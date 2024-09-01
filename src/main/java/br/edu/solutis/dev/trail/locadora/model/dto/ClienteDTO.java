package br.edu.solutis.dev.trail.locadora.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ClienteDTO {

    @NotBlank(message = "O campo nome não pode estar vazio")
    @Schema(example = "João da Silva")
    private String nome;

    @NotBlank(message = "O CPF não pode estar vazio")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve ter exatamente 11 dígitos")
    @Schema(example = "12345678901")
    private String cpf;

    @NotBlank(message = "O campo email não pode estar vazio")
    @Email(message = "O email deve ser válido")
    @Schema(example = "joao.silva@example.com")
    private String email;

    @NotBlank(message = "O campo data de nascimento não pode estar vazio")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Schema(example = "2000/01/01")
    private LocalDate dataNascimento;

    @NotBlank(message = "O campo sexo não pode estar vazio")
    @Pattern(regexp = "^(MASCULINO|MASCULINO)$", message = "O sexo deve ser Masculino ou Feminino")
    @Schema(example = "MASCULINO")
    private String sexo;

    @NotBlank(message = "O campo CNH não pode estar vazio")
    @Pattern(regexp = "\\d{11}", message = "A CNH deve ter exatamente 11 dígitos")
    @Schema(example = "98765432100")
    private String cnh;

    @JsonIgnore
    private List<AluguelDTO> alugueis;
}
