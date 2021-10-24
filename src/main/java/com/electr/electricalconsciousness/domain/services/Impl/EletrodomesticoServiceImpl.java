package com.electr.electricalconsciousness.domain.services.Impl;

import com.electr.electrical.domain.dto.eletrodomesticos.EletrodomesticoFullDTO;
import com.electr.electrical.domain.models.Eletrodomestico;
import com.electr.electrical.domain.repositories.EletrodomesticoRepository;
import com.electr.electrical.domain.services.EletrodomesticoService;
import com.electr.electrical.domain.utils.EletrodomesticoConverter;
import com.electr.electrical.exceptions.AllException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EletrodomesticoServiceImpl implements EletrodomesticoService {

    private final EletrodomesticoRepository eletrodomesticoRepository;
    private final EletrodomesticoConverter eletrodomesticoConverter;

    @Override
    public List<EletrodomesticoFullDTO> buscarTodosEletrodomesticos(){
        List<EletrodomesticoFullDTO> usuarios = eletrodomesticoConverter.toCollectionModelEletrodomesticoFull(eletrodomesticoRepository.findAll());

        if(usuarios.isEmpty()){
            throw new AllException("A lista de eletrodomésticos se encontra vazia", HttpStatus.NO_CONTENT);
        }

        return usuarios;
    }

    @Override
    public EletrodomesticoFullDTO buscarEletrodomesticoPorId(Long eletroId){
        return eletrodomesticoConverter.toEletrodomesticoDTO(eletrodomesticoRepository.findById(eletroId)
                .orElseThrow(() -> new AllException("Eletrodoméstico não encontrado", HttpStatus.NOT_FOUND)));
    }

    @Override
    public EletrodomesticoFullDTO salvarEletrodomestico(
            MultipartFile avatar, String nome, Integer potencia,
            Integer tempo, Integer quantidade, Integer diasPorMes){


        Eletrodomestico eletrodomestico = Eletrodomestico.builder()
                .setNome(nome)
                .setPotencia(potencia)
                .setQuantidade(quantidade)
                .setTempoEmHora(tempo)
                .setDiasPorMes(diasPorMes)
                .build();

        return eletrodomesticoConverter.toEletrodomesticoDTO(eletrodomesticoRepository.save(eletrodomestico));
    }


    @Override
    public EletrodomesticoFullDTO atualizarEletrodomestico(Long eletroId, String nome, Integer potencia, MultipartFile avatar){

        Eletrodomestico eletrodomesticoExistente = eletrodomesticoRepository.findById(eletroId)
                .orElseThrow(() -> new AllException("Eletrodoméstico não encontrado", HttpStatus.NOT_FOUND));

        if(nome != null) {
            eletrodomesticoExistente.setNome(nome);
        }

        if(potencia != null) {
            eletrodomesticoExistente.setPotencia(potencia);
        }

        return eletrodomesticoConverter.toEletrodomesticoDTO(eletrodomesticoRepository.save(eletrodomesticoExistente));
    }

    @Override
    public ResponseEntity<Void> deletarEletrodomestico(Long eletroId){

        EletrodomesticoFullDTO eletrodomestico = buscarEletrodomesticoPorId(eletroId);

        if( eletrodomestico == null ) {
            throw new AllException("Não existe eletrodoméstico com este identificador");
        }

        eletrodomesticoRepository.deleteById(eletroId);

        return ResponseEntity.noContent().build();

    }
}
