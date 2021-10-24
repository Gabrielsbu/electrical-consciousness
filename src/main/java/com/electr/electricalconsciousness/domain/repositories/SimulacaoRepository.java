package com.electr.electricalconsciousness.domain.repositories;

import com.electr.electricalconsciousness.domain.models.Simulacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimulacaoRepository extends JpaRepository<Simulacao, Long> {
}