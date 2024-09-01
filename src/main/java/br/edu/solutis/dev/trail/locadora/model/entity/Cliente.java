package br.edu.solutis.dev.trail.locadora.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Entity
@DiscriminatorValue(value = "CLIENTE")
public class Cliente extends Pessoa {

    @Column(length = 50, nullable = false, unique = true)
    private String cnh;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Aluguel> alugueis;

    // Construtor padrão
    public Cliente() {}

    // Construtor com parâmetros
    public Cliente(String nome, String cpf, String email, LocalDate dataNascimento, Sexo sexo, String cnh) {
        super(nome, cpf, email, dataNascimento, sexo);
        this.cnh = cnh;
    }
}
