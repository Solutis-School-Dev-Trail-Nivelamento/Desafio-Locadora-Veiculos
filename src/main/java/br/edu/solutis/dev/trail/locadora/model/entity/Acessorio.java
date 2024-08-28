package br.edu.solutis.dev.trail.locadora.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Entity
public class Acessorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String descricao;

    @ManyToMany(mappedBy = "acessorios")
    private List<Carro> carros = new ArrayList<>();
}
