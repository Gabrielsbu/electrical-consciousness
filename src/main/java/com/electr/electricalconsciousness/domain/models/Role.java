package com.electr.electricalconsciousness.domain.models;

import lombok.*;

import javax.persistence.*;

@Entity(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private String roleLabel;

    @Column(name = "role_authorities")
    private String authorities;

    private String roleLabelPt;
}
