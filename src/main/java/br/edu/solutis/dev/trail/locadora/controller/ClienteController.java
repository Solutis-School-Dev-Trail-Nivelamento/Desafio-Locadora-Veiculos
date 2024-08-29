package br.edu.solutis.dev.trail.locadora.controller;

import br.edu.solutis.dev.trail.locadora.exceptions.BusinessException;
import br.edu.solutis.dev.trail.locadora.service.ClienteService;
import br.edu.solutis.dev.trail.locadora.model.entity.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @Operation(summary = "Cadastrando novo cliente", description = "Cadastrando novo cliente e retornando os dados criados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Motorista criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "422", description = "Erro de negócio"),
            @ApiResponse(responseCode = "409", description = "Conflito de dados, possível duplicação")
    })
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
}
