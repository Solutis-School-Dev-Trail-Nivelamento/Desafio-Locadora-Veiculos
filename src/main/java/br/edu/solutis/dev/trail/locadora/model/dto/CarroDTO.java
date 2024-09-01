package br.edu.solutis.dev.trail.locadora.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CarroDTO {

    private Long id;

    @NotBlank(message = "A placa do carro não pode estar vazia")
    @Pattern(regexp = "[A-Z]{3}-\\d{4}", message = "A placa deve seguir o formato ABC-1234")
    private String placa;

    @NotBlank(message = "O chassi do carro não pode estar vazio")
    private String chassi;

    @NotBlank(message = "A cor do carro não pode estar vazia")
    private String cor;

    @NotNull(message = "O valor da diária não pode ser nulo")
    @Positive(message = "O valor da diária deve ser positivo")
    private BigDecimal valorDiaria;

    @NotNull(message = "O modelo do carro não pode ser nulo")
    private ModeloCarroDTO modelo;

    private List<AcessorioDTO> acessorios;
}
