package br.edu.solutis.dev.trail.locadora.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import lombok.Data;

@Data
public class AluguelDTO {
    private Calendar dataPedido;
    private LocalDate dataEntrega;
    private LocalDate dataDevolucao;
    private BigDecimal valorTotal;
    private String termos;
}
