package br.edu.solutis.dev.trail.locadora.controller;

import br.edu.solutis.dev.trail.locadora.model.entity.Aluguel;
import br.edu.solutis.dev.trail.locadora.service.AluguelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/aluguel")
@Tag(name = "Aluguel")
public class AluguelController {
    @Autowired
    private AluguelService aluguelService;

    @PostMapping("/criar/")
    public ResponseEntity<Aluguel> criarAluguel(
            @RequestParam Long clienteId, @RequestParam Long carroId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataEntrega,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dataDevolucao){
        Aluguel aluguel = aluguelService.criarAluguel(clienteId, carroId, dataEntrega, dataDevolucao);
        return ResponseEntity.ok(aluguel);
    }
    @GetMapping
    public ResponseEntity<List<Aluguel>> obterTodos() {
        List<Aluguel> alugueis = aluguelService.obterTodos();
        return ResponseEntity.ok(alugueis);
    }

    @PutMapping("/atualizar/{aluguelId}")
    public ResponseEntity<Aluguel> atualizarAluguel(
            @PathVariable Long aluguelId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dataEntrega,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataDevolucao) {
        Aluguel aluguel = aluguelService.atualizarAluguel(aluguelId, dataEntrega, dataDevolucao);
        return ResponseEntity.ok(aluguel);
    }

    @DeleteMapping("/deletar/{aluguelId}")
    public ResponseEntity<Void> deletarAluguel(@PathVariable Long aluguelId) {
        aluguelService.deletarAluguel(aluguelId);
        return ResponseEntity.noContent().build();
    }
}
