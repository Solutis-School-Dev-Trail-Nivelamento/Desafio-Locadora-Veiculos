package br.edu.solutis.dev.trail.locadora.model;

import jakarta.persistence.*;

@Entity(name = "tb_funcionario")
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
