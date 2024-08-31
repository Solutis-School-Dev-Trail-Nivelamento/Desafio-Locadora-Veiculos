package br.edu.solutis.dev.trail.locadora.controller;

import br.edu.solutis.dev.trail.locadora.model.entity.Aluguel;
import br.edu.solutis.dev.trail.locadora.model.entity.Carrinho;
import br.edu.solutis.dev.trail.locadora.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoController {
    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/criar")
    public Carrinho criarCarrinho() {
        return carrinhoService.criarCarrinho();
    }

    /*@PostMapping("/{carrinhoId}/adicionar/{veiculoId}/{clienteId}")
    public Aluguel adicionarVeiculo(@PathVariable Long carrinhoId, @PathVariable Long veiculoId, @PathVariable Long clienteId, @RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim) {
        return carrinhoService.adicionarCarro(carrinhoId, veiculoId, clienteId, dataInicio, dataFim);
    }*/

    @PostMapping("/{carrinhoId}/confirmar")
    public Carrinho confirmarReserva(@PathVariable Long carrinhoId) {
        return carrinhoService.confirmarReserva(carrinhoId);
    }
}
