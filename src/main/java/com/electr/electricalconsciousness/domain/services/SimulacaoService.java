package com.electr.electricalconsciousness.domain.services;

import com.electr.electricalconsciousness.domain.dto.simulacao.CreateSimulacaoDTO;
import com.electr.electricalconsciousness.domain.dto.simulacao.SimulacaoDTO;
import com.electr.electricalconsciousness.domain.models.Simulacao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SimulacaoService {

    SimulacaoDTO procurarSimulacaoPorId(Long simulacaoId);

    SimulacaoDTO salvarSimulacao(CreateSimulacaoDTO simulacao);

    List<SimulacaoDTO> procurarSimulacoes();
}
