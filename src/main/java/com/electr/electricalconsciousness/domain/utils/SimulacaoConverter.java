package com.electr.electricalconsciousness.domain.utils;

import com.electr.electricalconsciousness.domain.dto.simulacao.SimulacaoDTO;
import com.electr.electricalconsciousness.domain.models.Simulacao;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SimulacaoConverter {

    SimulacaoDTO toDTO(Simulacao simulacao);
    List<SimulacaoDTO> toCollectionDTO(List<Simulacao> simulacoes);
}
