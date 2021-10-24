package com.electr.electricalconsciousness.domain.utils;

import com.electr.electricalconsciousness.domain.dto.eletrodomesticos.EletrodomesticoDTO;
import com.electr.electricalconsciousness.domain.dto.eletrodomesticos.EletrodomesticoFullDTO;
import com.electr.electricalconsciousness.domain.models.Eletrodomestico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EletrodomesticoConverter {

    @Mapping(source = "mediaPicture.mediaUrl", target = "mediaUrl")
    EletrodomesticoDTO toModelEletrodomestico(Eletrodomestico eletrodomestico);

    @Mapping(source = "mediaPicture.mediaUrl", target = "mediaUrl")
    EletrodomesticoFullDTO toEletrodomesticoDTO(Eletrodomestico eletrodomestico);

    @Mapping(source = "mediaUrl", target = "mediaPicture.mediaUrl")
    List<Eletrodomestico> toCollectionModelEletrodomestico(List<EletrodomesticoDTO> eletrodomesticos);

    @Mapping(source = "mediaPicture.mediaUrl", target = "mediaUrl")
    List<EletrodomesticoFullDTO> toCollectionModelEletrodomesticoFull(List<Eletrodomestico> eletrodomesticos);
}
