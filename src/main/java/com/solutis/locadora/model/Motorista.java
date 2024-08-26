package com.solutis.locadora.model;

import jakarta.persistence.*;

@Entity(name = "tb_motorista")
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
