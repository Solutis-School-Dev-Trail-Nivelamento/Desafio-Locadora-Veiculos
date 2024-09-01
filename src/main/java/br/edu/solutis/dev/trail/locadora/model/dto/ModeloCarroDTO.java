package br.edu.solutis.dev.trail.locadora.model.dto;

import br.edu.solutis.dev.trail.locadora.model.entity.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModeloCarroDTO {

    private Long id;

    @NotBlank(message = "A descrição do modelo não pode estar vazia")
    private String descricao;

    @NotNull(message = "A categoria não pode ser nula")
    private Categoria categoria;

    @NotNull(message = "O fabricante não pode ser nulo")
    private FabricanteDTO fabricante;
}
