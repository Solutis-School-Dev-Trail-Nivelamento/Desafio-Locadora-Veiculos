package br.edu.solutis.dev.trail.locadora.model.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PagamentoDTO {
    private Long id;

    @NotBlank(message = "Método de pagamento")
    private String metodoPagamento;

    @NotBlank(message = "O nome do titular do cartão não pode ser vazio")
    private String titularCartao;

    @NotBlank(message = "O número do cartão não pode ser vazio")
    private String numeroCartao;

    @NotBlank(message = "A data de expiração não pode estar vazia")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/\\d{2}$", message = "A data de expiração deve estar no formato MM/AA")
    private String dataExpiracao;

    @Min(value = 100, message = "O CVV deve ter pelo menos 3 dígitos")
    @Digits(integer = 4, fraction = 0, message = "O CVV deve ter entre 3 e 4 dígitos")
    private int cvv;
}
