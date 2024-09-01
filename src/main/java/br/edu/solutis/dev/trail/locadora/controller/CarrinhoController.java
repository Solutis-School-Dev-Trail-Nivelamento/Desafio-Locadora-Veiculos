package br.edu.solutis.dev.trail.locadora.controller;

import br.edu.solutis.dev.trail.locadora.model.entity.Aluguel;
import br.edu.solutis.dev.trail.locadora.model.entity.Carrinho;
import br.edu.solutis.dev.trail.locadora.service.CarrinhoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("api/carrinho")
@Tag(name = "Carrinho")
public class CarrinhoController {
    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/{carrinhoId}/adicionar-aluguel")
    @Operation(summary = "Adicionar aluguel ao carrinho", description = "Cria uma aluguel no carrinho")
    public ResponseEntity<Carrinho> adicionarAluguelExistente(@PathVariable Long carrinhoId, @RequestParam Long aluguelId) {
        Carrinho carrinho = carrinhoService.adicionarAluguelExistente(carrinhoId, aluguelId);
        return ResponseEntity.ok(carrinho);
    }

    @GetMapping("/{carrinhoId}")
    @Operation(summary = "Obtem os dados do carrinho", description = "Mostra os dados adicionados no carrinho ")
    public ResponseEntity<Carrinho> obterCarrinho(@PathVariable Long carrinhoId) {
        Carrinho carrinho = carrinhoService.obterCarrinho(carrinhoId);
        return ResponseEntity.ok(carrinho);
    }

    @PostMapping("/{carrinhoId}/confirmar-reserva")
    @Operation(summary = "Confirma a reserva", description = "Mostra os dados adicionados no carrinho ")
    public ResponseEntity<Carrinho> confirmarReserva(@PathVariable Long carrinhoId) {
        Carrinho carrinho = carrinhoService.confirmarReserva(carrinhoId);
        return ResponseEntity.ok(carrinho);
    }

    @PutMapping("/alugueis/{aluguelId}")
    @Operation(summary = "Altera os dados da reserva", description = "Mostra os dados adicionados no carrinho ")
    public ResponseEntity<Aluguel> atualizarAluguel(@PathVariable Long aluguelId, @RequestParam LocalDate dataEntrega, @RequestParam LocalDate dataDevolucao) {
        Aluguel aluguel = carrinhoService.atualizarAluguel(aluguelId, dataEntrega, dataDevolucao);
        return ResponseEntity.ok(aluguel);
    }
}
