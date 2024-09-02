package br.edu.solutis.dev.trail.locadora.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long carrinhoId;
    private BigDecimal valorTotal;
    private LocalDateTime dataPagamento;
    private String metodoPagamento;
    private String numeroCartao;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AluguelStatus status;


    public Pagamento() {}

    public Pagamento(Long carrinhoId, BigDecimal valorTotal, String metodoPagamento, String numeroCartao) {
        this.carrinhoId = carrinhoId;
        this.valorTotal = valorTotal;
        this.dataPagamento = LocalDateTime.now();
        this.metodoPagamento = metodoPagamento;
        this.numeroCartao = numeroCartao;
    }
}
