package br.edu.solutis.dev.trail.locadora.controller;

import br.edu.solutis.dev.trail.locadora.model.dto.CarroDTO;
import br.edu.solutis.dev.trail.locadora.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
@RequiredArgsConstructor
@Validated
@Tag(name = "Carro")
public class CarroController {

    private final CarroService carroService;

    @PostMapping
    @Operation(summary = "Cria um novo carro", description = "Cria um novo carro com as informações fornecidas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carro criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<CarroDTO> create(
            @Parameter(description = "Dados do carro a ser criado", required = true)
            @Valid @RequestBody CarroDTO carroDTO) {
        try {
            CarroDTO createdCarroDTO = carroService.create(carroDTO);
            return ResponseEntity.status(201).body(createdCarroDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null); // Customize as needed
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um carro pelo ID", description = "Recupera os detalhes de um carro usando o ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro encontrado"),
            @ApiResponse(responseCode = "404", description = "Carro não encontrado com o ID fornecido")
    })
    public ResponseEntity<CarroDTO> findById(
            @Parameter(description = "ID do carro a ser recuperado", required = true)
            @PathVariable Long id) {
        CarroDTO carroDTO = carroService.findById(id);
        return carroDTO != null ? ResponseEntity.ok(carroDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Obtém todos os carros", description = "Recupera a lista de todos os carros disponíveis na locadora")
    @ApiResponse(responseCode = "200", description = "Lista de carros recuperada com sucesso")
    public ResponseEntity<List<CarroDTO>> findAll() {
        List<CarroDTO> carros = carroService.findAll();
        return ResponseEntity.ok(carros);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um carro existente", description = "Atualiza as informações do carro com o ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Carro não encontrado com o ID fornecido"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<CarroDTO> update(
            @Parameter(description = "ID do carro a ser atualizado", required = true)
            @PathVariable Long id,
            @Parameter(description = "Dados atualizados do carro", required = true)
            @Valid @RequestBody CarroDTO carroDTO) {
        try {
            CarroDTO updatedCarroDTO = carroService.update(id, carroDTO);
            return ResponseEntity.ok(updatedCarroDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null); // Customize as needed
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um carro pelo ID", description = "Remove o carro com o ID fornecido da locadora")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Carro removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Carro não encontrado com o ID fornecido")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do carro a ser removido", required = true)
            @PathVariable Long id) {
        if (carroService.findById(id) != null) {
            carroService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
