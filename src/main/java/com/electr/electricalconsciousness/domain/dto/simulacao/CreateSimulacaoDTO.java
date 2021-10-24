package com.electr.electricalconsciousness.domain.dto.simulacao;

import com.electr.electrical.domain.dto.eletrodomesticos.EletrodomesticoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateSimulacaoDTO {

    private Long usuarioId;
    private List<EletrodomesticoDTO> eletrodomesticos = new ArrayList<>();
}
