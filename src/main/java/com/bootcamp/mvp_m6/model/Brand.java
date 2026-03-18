package com.bootcamp.mvp_m6.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String name;

    @OneToMany(
            mappedBy = "brand",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Product> products = new HashSet<>();
}
