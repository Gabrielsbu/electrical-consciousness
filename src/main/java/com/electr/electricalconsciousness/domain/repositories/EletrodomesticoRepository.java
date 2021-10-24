package com.electr.electricalconsciousness.domain.repositories;

import com.electr.electricalconsciousness.domain.models.Eletrodomestico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EletrodomesticoRepository extends JpaRepository<Eletrodomestico, Long> {
}