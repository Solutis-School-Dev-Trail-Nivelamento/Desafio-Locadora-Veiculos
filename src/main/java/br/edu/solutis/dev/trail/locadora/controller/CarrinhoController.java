package br.edu.solutis.dev.trail.locadora.controller;

import br.edu.solutis.dev.trail.locadora.model.entity.Carrinho;
import br.edu.solutis.dev.trail.locadora.service.CarrinhoService;
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

   /* @PostMapping("/criar")
    public Carrinho criarCarrinho() {
        return carrinhoService.criarCarrinho();
    }*/

    @PostMapping("/{carrinhoId}/adicionar-veiculo")
    public ResponseEntity<Carrinho> adicionarVeiculo(@PathVariable Long carrinhoId, @RequestParam Long veiculoId, @RequestParam Long clienteId, @RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim) {
        Carrinho carrinho = carrinhoService.adicionarVeiculo(carrinhoId, veiculoId, clienteId, dataInicio, dataFim);
        return ResponseEntity.ok(carrinho);
    }

    @PostMapping("/{carrinhoId}/confirmar-reserva")
    public ResponseEntity<Carrinho> confirmarReserva(@PathVariable Long carrinhoId) {
        Carrinho carrinho = carrinhoService.confirmarReserva(carrinhoId);
        return ResponseEntity.ok(carrinho);
    }
}
