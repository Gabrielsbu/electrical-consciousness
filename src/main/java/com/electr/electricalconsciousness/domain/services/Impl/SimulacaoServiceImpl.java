package com.electr.electricalconsciousness.domain.services.Impl;

import com.electr.electricalconsciousness.domain.dto.eletrodomesticos.EletrodomesticoDTO;
import com.electr.electricalconsciousness.domain.dto.simulacao.CreateSimulacaoDTO;
import com.electr.electricalconsciousness.domain.dto.simulacao.SimulacaoDTO;
import com.electr.electricalconsciousness.domain.models.Eletrodomestico;
import com.electr.electricalconsciousness.domain.models.Simulacao;
import com.electr.electricalconsciousness.domain.models.Usuario;
import com.electr.electricalconsciousness.domain.repositories.EletrodomesticoRepository;
import com.electr.electricalconsciousness.domain.repositories.SimulacaoRepository;
import com.electr.electricalconsciousness.domain.repositories.UsuarioRepository;
import com.electr.electricalconsciousness.domain.services.SimulacaoService;
import com.electr.electricalconsciousness.domain.utils.EletrodomesticoConverter;
import com.electr.electricalconsciousness.domain.utils.SimulacaoConverter;
import com.electr.electricalconsciousness.exceptions.AllException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SimulacaoServiceImpl implements SimulacaoService {

    private final SimulacaoRepository simulacaoRepository;
    private final EletrodomesticoRepository eletrodomesticoRepository;
    private final SimulacaoConverter simulacaoConverter;
    private final EletrodomesticoConverter eletrodomesticoConverter;

    @Override
    public SimulacaoDTO procurarSimulacaoPorId(Long simulacaoId){
        return simulacaoConverter.toDTO(simulacaoRepository.findById(simulacaoId)
                .orElseThrow(() -> new AllException("Simulação não encontrada", HttpStatus.NOT_FOUND)));
    }

    @Override
    public SimulacaoDTO salvarSimulacao(CreateSimulacaoDTO simulacao) {

        Simulacao createSimulacao = new Simulacao();

        List<Eletrodomestico> eletrodomesticos = new ArrayList<>();

        float totalKwhPorMes = 0;
        float totalPorMes = 0;

        for(EletrodomesticoDTO eletrodomestico: simulacao.getEletrodomesticos()) {
            if(eletrodomestico.getEletroId() != null) {
                Eletrodomestico eletrodomesticoExistente =
                        eletrodomesticoRepository.findById(eletrodomestico.getEletroId())
                                .orElseThrow(() -> new AllException("Eletrodomestico não encontrado", HttpStatus.NOT_FOUND));

                eletrodomesticoExistente.setKwhPorMes(calcularKWh(eletrodomesticoExistente).getKwhPorMes());
                eletrodomesticoExistente.setValorPorMes(calcularKWh(eletrodomesticoExistente).getValorPorMes());
                totalKwhPorMes = totalKwhPorMes + eletrodomesticoExistente.getKwhPorMes();
                totalPorMes = totalPorMes + eletrodomesticoExistente.getValorPorMes();

                eletrodomesticos.add(eletrodomesticoExistente);
            }
        }

        createSimulacao.setTotalKwhPorMes(totalKwhPorMes);
        createSimulacao.setTotalValorPorMes(totalPorMes);
        createSimulacao.setEletrodomesticos(eletrodomesticos);

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
