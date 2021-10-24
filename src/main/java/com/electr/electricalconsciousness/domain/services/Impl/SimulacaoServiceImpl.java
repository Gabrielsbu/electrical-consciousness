package com.electr.electricalconsciousness.domain.services.Impl;

import com.electr.electrical.domain.dto.eletrodomesticos.EletrodomesticoDTO;
import com.electr.electrical.domain.dto.simulacao.CreateSimulacaoDTO;
import com.electr.electrical.domain.dto.simulacao.SimulacaoDTO;
import com.electr.electrical.domain.models.Eletrodomestico;
import com.electr.electrical.domain.models.Simulacao;
import com.electr.electrical.domain.models.Usuario;
import com.electr.electrical.domain.repositories.EletrodomesticoRepository;
import com.electr.electrical.domain.repositories.SimulacaoRepository;
import com.electr.electrical.domain.repositories.UsuarioRepository;
import com.electr.electrical.domain.services.SimulacaoService;
import com.electr.electrical.domain.utils.EletrodomesticoConverter;
import com.electr.electrical.domain.utils.SimulacaoConverter;
import com.electr.electrical.exceptions.AllException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SimulacaoServiceImpl implements SimulacaoService {

    private final SimulacaoRepository simulacaoRepository;
    private final EletrodomesticoRepository eletrodomesticoRepository;
    private final UsuarioRepository usuarioRepository;
    private final SimulacaoConverter simulacaoConverter;
    private final EletrodomesticoConverter eletrodomesticoConverter;

    @Override
    public SimulacaoDTO procurarSimulacaoPorId(Long simulacaoId){
        return simulacaoConverter.toDTO(simulacaoRepository.findById(simulacaoId)
                .orElseThrow(() -> new AllException("Simulação não encontrada")));
    }

    @Override
    public SimulacaoDTO salvarSimulacao(CreateSimulacaoDTO simulacao) {
        Usuario usuario = usuarioRepository.findById(simulacao.getUsuarioId()).
                orElseThrow(() -> new AllException("Usuário não encontrado"));

        Simulacao createSimulacao = new Simulacao();

        List<EletrodomesticoDTO> eletrodomesticos = new ArrayList<>();

        float totalKwhPorMes = 0;

        float totalPorMes = 0;

        for(EletrodomesticoDTO eletrodomestico : simulacao.getEletrodomesticos()) {

            if(eletrodomestico.getEletroId() != null) {
                Eletrodomestico eletrodomesticoExistente =
                        eletrodomesticoRepository.findById(eletrodomestico.getEletroId())
                                .orElseThrow(() -> new AllException("Eletrodomestico não encontrado"));

                eletrodomesticoExistente.setKwhPorMes(calcularKWh(eletrodomesticoExistente).getKwhPorMes());
                eletrodomesticoExistente.setValorPorMes(calcularKWh(eletrodomesticoExistente).getValorPorMes());
                totalKwhPorMes = totalKwhPorMes + eletrodomesticoExistente.getKwhPorMes();
                totalPorMes = totalPorMes + eletrodomesticoExistente.getValorPorMes();

                eletrodomesticos.add(eletrodomesticoConverter.toModelEletrodomestico(eletrodomesticoExistente));

            } else {
                Eletrodomestico eletrodomesticoCreate = new Eletrodomestico();
                eletrodomesticoCreate.setNome(eletrodomestico.getNome());
                eletrodomesticoCreate.setDiasPorMes(eletrodomestico.getDiasPorMes());
                eletrodomesticoCreate.setPotencia(eletrodomestico.getPotencia());
                eletrodomesticoCreate.setQuantidade(eletrodomestico.getQuantidade());
                eletrodomesticoCreate.setTempoEmHora(eletrodomestico.getTempoEmHora());
                eletrodomesticoCreate.setKwhPorMes(calcularKWh(eletrodomesticoCreate).getKwhPorMes());
                eletrodomesticoCreate.setValorPorMes(calcularKWh(eletrodomesticoCreate).getValorPorMes());
                totalKwhPorMes = totalKwhPorMes + eletrodomesticoCreate.getKwhPorMes();
                totalPorMes = totalPorMes + eletrodomesticoCreate.getValorPorMes();

                eletrodomesticos.add(eletrodomesticoConverter.toModelEletrodomestico(eletrodomesticoCreate));
            }
        }

        createSimulacao.setTotalKwhPorMes(totalKwhPorMes);
        createSimulacao.setTotalValorPorMes(totalPorMes);
        createSimulacao.setEletrodomesticos(eletrodomesticoConverter.toCollectionModelEletrodomestico(eletrodomesticos));
        createSimulacao.setUsuario(usuario);

        return simulacaoConverter.toDTO(simulacaoRepository.save(createSimulacao));
    }

    private Eletrodomestico calcularKWh(Eletrodomestico eletrodomestico){

         long kWhPorMes = ( (long) eletrodomestico.getPotencia() * eletrodomestico.getTempoEmHora() * eletrodomestico.getDiasPorMes() * eletrodomestico.getQuantidade())/1000;
         eletrodomestico.setKwhPorMes(kWhPorMes);
         eletrodomestico.setValorPorMes(kWhPorMes * 0.82f);
         return eletrodomestico;
    }

    @Override
    public List<SimulacaoDTO> procurarSimulacoes() {
        return simulacaoConverter.toCollectionDTO(simulacaoRepository.findAll());
    }
}
