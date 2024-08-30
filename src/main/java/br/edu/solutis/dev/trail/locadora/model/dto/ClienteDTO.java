package br.edu.solutis.dev.trail.locadora.model.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private String nome;
    private String cpf;
    private String email;
    private String dataNascimento;
    private String sexo;
    private String cnh;
}
