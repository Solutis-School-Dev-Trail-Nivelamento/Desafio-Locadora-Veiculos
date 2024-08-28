package br.edu.solutis.dev.trail.locadora.controller;

import br.edu.solutis.dev.trail.locadora.exceptions.BusinessException;
import br.edu.solutis.dev.trail.locadora.model.entity.Funcionario;
import br.edu.solutis.dev.trail.locadora.model.entity.Motorista;
import br.edu.solutis.dev.trail.locadora.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    @Operation(summary = "Cadastrando novo funcionário", description = "Cadastrando novo funcionário e retornando os dados criados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Motorista criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<String> cadastrar(@RequestBody Funcionario funcionario) {
        try {
            funcionarioService.salvar(funcionario);
            return new ResponseEntity<>("Cadastro realizado com sucesso!", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Cadastro não realizado: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (BusinessException e) {
            return new ResponseEntity<>("Erro de negócio: " + e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
