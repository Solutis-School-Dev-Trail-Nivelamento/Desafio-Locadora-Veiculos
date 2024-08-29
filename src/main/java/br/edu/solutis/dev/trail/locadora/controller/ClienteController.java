package br.edu.solutis.dev.trail.locadora.controller;

import br.edu.solutis.dev.trail.locadora.exceptions.BusinessException;
import br.edu.solutis.dev.trail.locadora.service.ClienteService;
import br.edu.solutis.dev.trail.locadora.model.entity.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @Operation(summary = "Cadastrando novo cliente", description = "Cadastrando novo cliente e retornando os dados criados")
    public ResponseEntity<?> cadastrar(@RequestBody Cliente cliente) {
        try {
            Cliente clienteSalvo = clienteService.salvar(cliente);
            return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Cadastro não realizado: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (BusinessException e) {
            return new ResponseEntity<>("Erro de negócio: " + e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Conflito de dados: " + e.getRootCause().getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("cliente/{id}")
    @Operation(summary = "Cadastrando novo cliente", description = "Cadastrando novo cliente e retornando os dados criados")
    public ResponseEntity<Cliente> obterCliente(@PathVariable Long id){
        Optional<Cliente> cliente = clienteService.obterPorId(id);
        return cliente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}
