package br.edu.solutis.dev.trail.locadora.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 10, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("P")
@Table(name = "tb_pessoa")
public class Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    @NotBlank
    @NotNull
    private String nome;

    @Column(length = 50, nullable = false, unique = true)
    private String cpf;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 50, nullable = false)
    private LocalDate dataNascimento;

    @Column(insertable=false, updatable=false)
    private String tipo;

    @Enumerated(EnumType.STRING)  // Mapeia o enum como String no banco de dados
    @Column(length = 10)
    private Sexo sexo;

    // Construtor padrão
    public Pessoa() {}

    // Construtor com parâmetros
    public Pessoa(String nome, String cpf, String email, LocalDate dataNascimento, Sexo sexo) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }
}
