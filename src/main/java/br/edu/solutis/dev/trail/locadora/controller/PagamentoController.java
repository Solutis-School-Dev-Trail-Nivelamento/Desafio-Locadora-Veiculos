package br.edu.solutis.dev.trail.locadora.controller;

import br.edu.solutis.dev.trail.locadora.model.dto.PagamentoDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.Pagamento;
import br.edu.solutis.dev.trail.locadora.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/pagamentos")
@Tag(name = "Pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/iniciar")
    @Operation(summary = "Iniciar pagamento", description = "Inicia um pagamento para o carrinho")
    public ResponseEntity<Pagamento> iniciarPagamento(@RequestParam Long carrinhoId, @Valid @RequestBody PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = pagamentoService.iniciarPagamento(carrinhoId, pagamentoDTO);
        return ResponseEntity.ok(pagamento);
    }

    @PostMapping("/confirmar")
    @Operation(summary = "Confirmar pagamento", description = "Confirma o pagamento")
    public ResponseEntity<Pagamento> confirmarPagamento(@RequestParam Long pagamentoId) {
        Pagamento pagamento = pagamentoService.confirmarPagamento(pagamentoId);
        return ResponseEntity.ok(pagamento);
    }
}
