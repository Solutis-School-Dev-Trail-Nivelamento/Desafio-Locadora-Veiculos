package br.edu.solutis.dev.trail.locadora.model.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue(value = "M")
public class Motorista extends Pessoa{

    @Column(length = 50, nullable = false, unique = true)
    private String cnh;

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }
}
