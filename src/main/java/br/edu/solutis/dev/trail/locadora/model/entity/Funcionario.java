package br.edu.solutis.dev.trail.locadora.model.entity;

import jakarta.persistence.*;

@DiscriminatorValue(value = "F")
public class Funcionario extends Pessoa{

    @Column(length = 50, nullable = false,unique = true)
    private String matricula;


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
