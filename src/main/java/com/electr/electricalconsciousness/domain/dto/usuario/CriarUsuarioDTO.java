package com.electr.electricalconsciousness.domain.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CriarUsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    private String avatar;
}
