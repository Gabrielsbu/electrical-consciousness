package com.electr.electricalconsciousness.domain.services;

import com.electr.electrical.domain.dto.eletrodomesticos.EletrodomesticoFullDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface EletrodomesticoService {

    List<EletrodomesticoFullDTO> buscarTodosEletrodomesticos();

    EletrodomesticoFullDTO buscarEletrodomesticoPorId(Long eletroId);

    EletrodomesticoFullDTO salvarEletrodomestico(MultipartFile avatar, String nome, Integer potencia, Integer tempo, Integer quantidade, Integer diasPorMes);

    EletrodomesticoFullDTO atualizarEletrodomestico(Long eletroId, String nome, Integer potencia, MultipartFile avatar);

    ResponseEntity<Void> deletarEletrodomestico(Long eletroId);
}
