package br.edu.solutis.dev.trail.locadora.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FabricanteDTO {

    private Long id;

    @NotBlank(message = "O nome do fabricante não pode estar vazio")
    private String nome;
}
