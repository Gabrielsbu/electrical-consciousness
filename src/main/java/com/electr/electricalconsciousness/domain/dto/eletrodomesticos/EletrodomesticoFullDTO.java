package com.electr.electricalconsciousness.domain.dto.eletrodomesticos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EletrodomesticoFullDTO {

    private Long eletroId;
    private String nome;
    private String mediaUrl;
    private Integer quantidade;
    private Integer tempoEmHora;
    private Integer potencia;
    private Integer diasPorMes;

    private float valorPorMes;
    private float kwhPorMes;
    private LocalDateTime createEletrodomesticoAt;
    private LocalDateTime updateEletrodomesticoAt;
}
