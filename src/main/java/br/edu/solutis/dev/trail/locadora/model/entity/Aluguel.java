package br.edu.solutis.dev.trail.locadora.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@ToString
@Entity
@Table(name = "tb_aluguel")
public class Aluguel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @ManyToOne
    @JoinColumn(name = "carro_id")
    private Carro carro;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "carrinho_id", nullable = false)
    @JsonIgnore
    private Carrinho carrinho;

    private LocalDateTime dataPedido;
    private LocalDate dataEntrega;
    private LocalDate dataDevolucao;
    private BigDecimal valorTotal;
    private int quantidadeDias;

    @Setter
    @Enumerated(EnumType.STRING)
    private AluguelStatus status;

    public Aluguel() {
    }

    public Aluguel(Cliente cliente, Carro carro, LocalDate dataEntrega, LocalDate dataDevolucao) {
        this.cliente = cliente;
        this.carro = carro;
        this.dataPedido = LocalDateTime.now();
        this.dataEntrega = dataEntrega;
        this.dataDevolucao = dataDevolucao;
        this.quantidadeDias = calcularQuantidadeDias();
        this.valorTotal = calcularValorTotal();
    }

    public int calcularQuantidadeDias() {
        return (int) ChronoUnit.DAYS.between(dataEntrega, dataDevolucao);
    }

    public BigDecimal calcularValorTotal() {
        int quantidadeDias = calcularQuantidadeDias();
        BigDecimal valorDiaria = carro.getValorDiaria();
        return valorDiaria.multiply(BigDecimal.valueOf(quantidadeDias));
    }
}

