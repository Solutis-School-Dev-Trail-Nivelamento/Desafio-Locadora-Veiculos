package br.edu.solutis.dev.trail.locadora.controller;

import br.edu.solutis.dev.trail.locadora.model.dto.ClienteDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.Aluguel;
import br.edu.solutis.dev.trail.locadora.model.entity.Cliente;
import br.edu.solutis.dev.trail.locadora.service.AluguelService;
import br.edu.solutis.dev.trail.locadora.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Tag(name = "Cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AluguelService aluguelService;

    @PostMapping("/cliente")
    @Operation(summary = "Cadastra um novo cliente", description = "Cadastra um novo cliente e retorna os dados criados")
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteCriadoDTO = clienteService.salvarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriadoDTO);
    }

    @GetMapping("/clientes")
    @Operation(summary = "Obtém todos clientes", description = "Retorna a informação com todos clientes cadastrados")
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> clientes = clienteService.obterTodos();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/cliente/{id}")
    @Operation(summary = "Obtém um cliente pelo ID", description = "Retorna cliente pelo ID")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Cliente cliente = clienteService.obterPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/cliente/{id}")
    @Operation(summary = "Atualiza um cliente pelo ID", description = "Atualiza as informações de um cliente")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/cliente/{id}")
    @Operation(summary = "Remove um cliente pelo ID", description = "Deleta cliente pelo ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{id}/alugueis-confirmados")
    @Operation(summary = "Exibe os aluguéis confirmados do cliente", description = "Exibe todos os aluguéis confirmados relacionados ao cliente pelo seu ID")
    public ResponseEntity<List<Aluguel>> alugueisConfirmados(@PathVariable Long id) {
        List<Aluguel> alugueis = aluguelService.buscarAlugueisConfirmados(id);
        return ResponseEntity.ok(alugueis);
    }
}
