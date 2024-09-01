package br.edu.solutis.dev.trail.locadora.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AcessorioDTO {

    private Long id;

    @NotBlank(message = "A descrição do acessório não pode estar vazia")
    private String descricao;
}
