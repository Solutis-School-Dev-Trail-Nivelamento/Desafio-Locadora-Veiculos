package br.edu.solutis.dev.trail.locadora.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

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

  /*  @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "apolice_id")
    private ApoliceSeguro apoliceSeguro;*/

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;

    private Calendar dataPedido;
    private LocalDate dataEntrega;
    private LocalDate dataDevolucao;
    private BigDecimal valorTotal;
    private int quantidadeDias;

    public Aluguel() {
    }

    public Aluguel(Cliente cliente, Carro carro, LocalDate dataEntrega, LocalDate dataDevolucao) {
        this.cliente = cliente;
        this.carro = carro;
        this.dataPedido = Calendar.getInstance();
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
