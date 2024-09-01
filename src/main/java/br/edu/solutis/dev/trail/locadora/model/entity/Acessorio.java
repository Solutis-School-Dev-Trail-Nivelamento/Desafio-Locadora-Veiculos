package br.edu.solutis.dev.trail.locadora.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Entity
@Table(name= "tb_acessorio")
public class Acessorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String descricao;

    @ManyToMany(mappedBy = "acessorios")
    private List<Carro> carros;
}