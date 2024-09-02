package br.edu.solutis.dev.trail.locadora.controller;

import br.edu.solutis.dev.trail.locadora.model.entity.Aluguel;
import br.edu.solutis.dev.trail.locadora.model.entity.AluguelStatus;
import br.edu.solutis.dev.trail.locadora.repository.AluguelRepository;
import br.edu.solutis.dev.trail.locadora.service.AluguelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Aluguel")
public class AluguelController {
    @Autowired
    private AluguelService aluguelService;

    @Autowired
    private AluguelRepository aluguelRepository;

    @PostMapping("/aluguel")
    @Operation(summary = "Cria um aluguel", description = "Cria um novo aluguel com as informações fornecidas")
    public ResponseEntity<Aluguel> criarAluguel(
            @RequestParam Long clienteId, @RequestParam Long carroId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataEntrega,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dataDevolucao){
        Aluguel aluguel = aluguelService.criarAluguel(clienteId, carroId, dataEntrega, dataDevolucao);
        return ResponseEntity.ok(aluguel);
    }

    @GetMapping(value = "/alugueis")
    @Operation(summary = "Obtem todos alugueis", description = "Obtem todos alugueis cadastrados")
    public ResponseEntity<List<Aluguel>> obterTodos() {
        List<Aluguel> alugueis = aluguelService.obterTodos();
        return ResponseEntity.ok(alugueis);
    }

    @PutMapping("/aluguel/{id}")
    @Operation(summary = "Atualiza os dados de uma aluguel", description = "Atualiza dos dados de um aluguel ja cadastrado")
    public ResponseEntity<Aluguel> atualizarAluguel(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dataEntrega,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataDevolucao) {
        Aluguel aluguel = aluguelService.atualizarAluguel(id, dataEntrega, dataDevolucao);
        return ResponseEntity.ok(aluguel);
    }

    @DeleteMapping("/aluguel/{id}")
    @Operation(summary = "Deleta um aluguel", description = "Cria um novo aluguel com as informações fornecidas")
    public ResponseEntity<Void> deletarAluguel(@PathVariable Long id) {
        aluguelService.deletarAluguel(id);
        return ResponseEntity.noContent().build();
    }

   /* @GetMapping("/carro/{carroId}/reservados")
    @Operation(summary = "Listar aluguéis reservados", description = "Lista todos os aluguéis reservados de um carro específico")
    public ResponseEntity<List<Aluguel>> listarAlugueisReservados(@PathVariable Long carroId) {
        List<Aluguel> alugueisReservados = aluguelRepository.findByCarroIdAndStatus(carroId, AluguelStatus.RESERVADO);
        return ResponseEntity.ok(alugueisReservados);
    }*/
}
