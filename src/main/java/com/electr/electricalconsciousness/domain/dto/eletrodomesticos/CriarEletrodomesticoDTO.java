package com.electr.electricalconsciousness.domain.dto.eletrodomesticos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CriarEletrodomesticoDTO {

    private String nome;
    private Integer tempoEmMinuto;
    private Integer quantidade;
    private Integer potencia;
    private Integer diasPorMes;
    private String avatar;
    private float valorPorMes;
}
