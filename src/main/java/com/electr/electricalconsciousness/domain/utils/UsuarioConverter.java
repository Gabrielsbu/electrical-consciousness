package com.electr.electricalconsciousness.domain.utils;

import com.electr.electricalconsciousness.domain.dto.usuario.UsuarioDTO;
import com.electr.electricalconsciousness.domain.models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioConverter {

    @Mapping(source = "mediaPicture.mediaUrl", target = "mediaUrl")
    UsuarioDTO toModelDTO(Usuario usuario);
    List<UsuarioDTO> toCollectionDTO(List<Usuario> usuarios);
}
