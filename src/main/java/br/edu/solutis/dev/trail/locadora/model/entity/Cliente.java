package br.edu.solutis.dev.trail.locadora.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Entity
@DiscriminatorValue(value = "CLIENTE")
public class Cliente extends Pessoa{

    @Column(length = 50, nullable = false, unique = true)
    private String cnh;

    @OneToMany(mappedBy = "cliente")
    private List<Aluguel> alugueis;
}
