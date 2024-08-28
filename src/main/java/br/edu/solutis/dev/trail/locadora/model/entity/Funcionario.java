package br.edu.solutis.dev.trail.locadora.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@DiscriminatorValue(value = "Funcionario")
public class Funcionario extends Pessoa{

    @Column(length = 50, nullable = false,unique = true)
    private String matricula;

}
