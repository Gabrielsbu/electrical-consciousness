package com.electr.electricalconsciousness.domain.dto.eletrodomesticos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EletrodomesticoDTO {

    private Long eletroId;
    private String nome;
    private String mediaUrl;
    private Integer quantidade;
    private Integer tempoEmHora;
    private Integer potencia;
    private Integer diasPorMes;
    private float valorPorMes;
    private float kwhPorMes;

}
