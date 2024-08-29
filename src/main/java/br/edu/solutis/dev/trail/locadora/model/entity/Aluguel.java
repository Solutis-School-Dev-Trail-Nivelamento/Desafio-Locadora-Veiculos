package br.edu.solutis.dev.trail.locadora.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Calendar;

@Data
@ToString
@Entity
public abstract class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Calendar dataPedido;

    @Column(nullable = false)
    private LocalDate dataEntrega;

    @Column(nullable = false)
    private LocalDate dataDevolucao;

    @Column(nullable = false)
    private float valorTotal;

    @Column(length = 1, nullable = false)
    private String tipo;

   /* @OneToOne
    @JoinColumn(name = "apolice_id")
    private ApoliceSeguro apoliceSeguro;*/

    @ManyToOne
    @JoinColumn(name = "motorista_id", nullable = false) // Relacionamento muitos-para-um com Motorista
    private Cliente cliente;

   @ManyToOne
    @JoinColumn(name = "carro_id", nullable = false) // Relacionamento muitos-para-um com Carro
    private Carro carro;
}