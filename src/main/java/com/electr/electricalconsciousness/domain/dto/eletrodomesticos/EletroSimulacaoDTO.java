package com.electr.electricalconsciousness.domain.dto.eletrodomesticos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EletroSimulacaoDTO {
    private Long eletroId;
    private Integer quantidade;
    private String nome;
    private Integer tempoEmHora;
    private float kwhPorMes;
    private float valorPorMes;
}
