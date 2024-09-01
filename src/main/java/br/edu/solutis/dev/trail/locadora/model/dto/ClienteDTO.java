package br.edu.solutis.dev.trail.locadora.model.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ClienteDTO {
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private String sexo;
    private String cnh;

}