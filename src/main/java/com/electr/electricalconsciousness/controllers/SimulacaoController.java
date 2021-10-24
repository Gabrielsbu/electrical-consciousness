package com.electr.electricalconsciousness.controllers;

import com.electr.electricalconsciousness.domain.dto.simulacao.CreateSimulacaoDTO;
import com.electr.electricalconsciousness.domain.dto.simulacao.SimulacaoDTO;
import com.electr.electricalconsciousness.domain.models.Simulacao;
import com.electr.electricalconsciousness.domain.services.SimulacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/simulacao")
@RestController
@RequiredArgsConstructor
public class SimulacaoController {

    private final SimulacaoService simulacaoService;

    @GetMapping("/{simulacaoId}")
    public SimulacaoDTO buscarSimulacaoPorId(@PathVariable Long simulacaoId) {
        return simulacaoService.procurarSimulacaoPorId(simulacaoId);
    }

    @GetMapping
    public List<SimulacaoDTO> buscarTodasSimulacoes() {
        return simulacaoService.procurarSimulacoes();
    }

    @PostMapping
    public SimulacaoDTO cadastrarSimulacao(@RequestBody CreateSimulacaoDTO simulacao){
        return simulacaoService.salvarSimulacao(simulacao);
    }
}
