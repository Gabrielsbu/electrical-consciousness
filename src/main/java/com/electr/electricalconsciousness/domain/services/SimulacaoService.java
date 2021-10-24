package com.electr.electricalconsciousness.domain.services;

import com.electr.electrical.domain.dto.simulacao.CreateSimulacaoDTO;
import com.electr.electrical.domain.dto.simulacao.SimulacaoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SimulacaoService {

    SimulacaoDTO procurarSimulacaoPorId(Long simulacaoId);

    SimulacaoDTO salvarSimulacao(CreateSimulacaoDTO simulacao);

    List<SimulacaoDTO> procurarSimulacoes();
}
