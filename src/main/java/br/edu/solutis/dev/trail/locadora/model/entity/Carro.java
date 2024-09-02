package br.edu.solutis.dev.trail.locadora.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Entity
@Table(name= "tb_carro")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String placa;

    @Column(length = 17, nullable = false, unique = true)
    private String chassi;

    @Column(length = 30, nullable = false)
    private String cor;

    @Column(nullable = false)
    private BigDecimal valorDiaria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modelo_id", nullable = false)
    private ModeloCarro modelo;

    @ManyToMany
    @JoinTable(
            name = "tb_carro_acessorio",
            joinColumns = @JoinColumn(name = "carro_id"),
            inverseJoinColumns = @JoinColumn(name = "acessorio_id")
    )

    @JsonManagedReference
    private List<Acessorio> acessorios = new ArrayList<>();


    // Construtor padrão
    public Carro() {}

    // Construtor com parâmetros
    public Carro(String placa, String chassi, String cor, BigDecimal valorDiaria, ModeloCarro modelo) {
        this.placa = placa;
        this.chassi = chassi;
        this.cor = cor;
        this.valorDiaria = valorDiaria;
        this.modelo = modelo;
    }
}
