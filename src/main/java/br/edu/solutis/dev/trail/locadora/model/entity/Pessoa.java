package br.edu.solutis.dev.trail.locadora.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo", length = 10, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("P")
public abstract class Pessoa {
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

}
