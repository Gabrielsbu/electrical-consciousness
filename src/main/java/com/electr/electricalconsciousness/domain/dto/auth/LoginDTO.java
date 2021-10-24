package com.electr.electricalconsciousness.domain.dto.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder(setterPrefix = "set")
public class LoginDTO {

    private String email;
    private String senha;
}
