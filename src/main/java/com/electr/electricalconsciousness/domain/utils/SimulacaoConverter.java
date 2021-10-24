package com.electr.electricalconsciousness.domain.utils;

import com.electr.electrical.domain.dto.simulacao.SimulacaoDTO;
import com.electr.electrical.domain.models.Simulacao;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SimulacaoConverter {

    List<SimulacaoDTO> toCollectionDTO(List<Simulacao> simulacoes);

    SimulacaoDTO toDTO(Simulacao simulacao);
}
