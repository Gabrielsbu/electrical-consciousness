package com.electr.electricalconsciousness.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "simulacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Simulacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long simulacaoId;

    @OneToMany
    private List<Eletrodomestico> eletrodomesticos = new ArrayList<>();

    @Column(scale = 2, precision = 10)
    private float totalValorPorMes;

    @Column(scale = 2, precision = 10)
    private float totalKwhPorMes;

    private LocalDateTime createSimulationAt;
    private LocalDateTime updateSimulationAt;

    @PrePersist
    public void prePersist(){
        this.createSimulationAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updateSimulationAt = LocalDateTime.now();
    }
}
