package br.edu.solutis.dev.trail.locadora.model.dto;


import java.math.BigDecimal;

public class ApoliceSeguroDTO {
    private BigDecimal valorFranquia;
    private boolean protecaTerceiro;
    private boolean protecaCausasNaturais;
    private boolean protecaRoubo;

    // Getters and Setters
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
