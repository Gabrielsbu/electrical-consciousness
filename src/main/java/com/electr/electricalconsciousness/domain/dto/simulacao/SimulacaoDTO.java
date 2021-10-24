package com.electr.electricalconsciousness.domain.dto.simulacao;

import com.electr.electricalconsciousness.domain.dto.eletrodomesticos.EletroSimulacaoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimulacaoDTO {

    private Long simulacaoId;
    private List<EletroSimulacaoDTO> eletrodomesticos = new ArrayList<>();

    private float totalValorPorMes;
    private float totalKwhPorMes;

    private LocalDateTime createSimulationAt;
    private LocalDateTime updateSimulationAt;
}
