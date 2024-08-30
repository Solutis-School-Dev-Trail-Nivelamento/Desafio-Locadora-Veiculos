package br.edu.solutis.dev.trail.locadora.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;

@Data
@ToString
@Entity
@Table(name = "tb_aluguel")
public class Aluguel implements Serializable {

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
    private BigDecimal valorTotal;

    @Column(length = 1, nullable = false)
    private String tipo;

    @Column(length = 100, nullable = false)
    private String termos;

   /* @OneToOne
    @JoinColumn(name = "apolice_id")
    private ApoliceSeguro apoliceSeguro;*/

    @ManyToOne
    @JoinColumn(name = "cliente_id") // Relacionamento muitos-para-um com Motorista
    private Cliente cliente;

   @ManyToOne
   @JoinColumn(name = "carro_id", nullable = false) // Relacionamento muitos-para-um com Carro
   private Carro carro;
}