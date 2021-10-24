package com.electr.electricalconsciousness.domain.utils;

import com.electr.electrical.domain.dto.eletrodomesticos.EletrodomesticoDTO;
import com.electr.electrical.domain.dto.eletrodomesticos.EletrodomesticoFullDTO;
import com.electr.electrical.domain.models.Eletrodomestico;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EletrodomesticoConverter {

    EletrodomesticoDTO toModelEletrodomestico(Eletrodomestico eletrodomestico);

    EletrodomesticoFullDTO toEletrodomesticoDTO(Eletrodomestico eletrodomestico);

    List<Eletrodomestico> toCollectionModelEletrodomestico(List<EletrodomesticoDTO> eletrodomesticos);

    List<EletrodomesticoFullDTO> toCollectionModelEletrodomesticoFull(List<Eletrodomestico> eletrodomesticos);
}
