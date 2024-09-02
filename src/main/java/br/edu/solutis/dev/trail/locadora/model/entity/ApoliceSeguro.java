package br.edu.solutis.dev.trail.locadora.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ApoliceSeguro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valorFranquia;
    private boolean protecaTerceiro;
    private boolean protecaCausasNaturais;
    private boolean protecaRoubo;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorFranquia() {
        return valorFranquia;
    }

    public void setValorFranquia(BigDecimal valorFranquia) {
        this.valorFranquia = valorFranquia;
    }

    public boolean isProtecaTerceiro() {
        return protecaTerceiro;
    }

    public void setProtecaTerceiro(boolean protecaTerceiro) {
        this.protecaTerceiro = protecaTerceiro;
    }

    public boolean isProtecaCausasNaturais() {
        return protecaCausasNaturais;
    }

    public void setProtecaCausasNaturais(boolean protecaCausasNaturais) {
        this.protecaCausasNaturais = protecaCausasNaturais;
    }

    public boolean isProtecaRoubo() {
        return protecaRoubo;
    }

    public void setProtecaRoubo(boolean protecaRoubo) {
        this.protecaRoubo = protecaRoubo;
    }
}