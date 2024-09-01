package br.edu.solutis.dev.trail.locadora.controller;

import br.edu.solutis.dev.trail.locadora.model.dto.ModeloCarroDTO;
import br.edu.solutis.dev.trail.locadora.service.ModeloCarroService;
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
@RequestMapping("/modelos-carros")
@RequiredArgsConstructor
@Validated
@Tag( name = "Modelo Carro")
public class ModeloCarroController {

    private final ModeloCarroService modeloCarroService;

    @PostMapping
    @Operation(summary = "Cria um novo modelo de carro", description = "Cria um novo modelo de carro com as informações fornecidas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Modelo de carro criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<ModeloCarroDTO> create(
            @Parameter(description = "Dados do modelo de carro a ser criado", required = true)
            @Valid @RequestBody ModeloCarroDTO modeloCarroDTO) {
        try {
            ModeloCarroDTO createdModeloCarroDTO = modeloCarroService.create(modeloCarroDTO);
            return ResponseEntity.status(201).body(createdModeloCarroDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null); // Customize as needed
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um modelo de carro pelo ID", description = "Recupera os detalhes do modelo de carro usando o ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modelo de carro encontrado"),
            @ApiResponse(responseCode = "404", description = "Modelo de carro não encontrado com o ID fornecido")
    })
    public ResponseEntity<ModeloCarroDTO> findById(
            @Parameter(description = "ID do modelo de carro a ser recuperado", required = true)
            @PathVariable Long id) {
        ModeloCarroDTO modeloCarroDTO = modeloCarroService.findById(id);
        return modeloCarroDTO != null ? ResponseEntity.ok(modeloCarroDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Obtém todos os modelos de carro", description = "Recupera a lista de todos os modelos de carro disponíveis na locadora")
    @ApiResponse(responseCode = "200", description = "Lista de modelos de carro recuperada com sucesso")
    public ResponseEntity<List<ModeloCarroDTO>> findAll() {
        List<ModeloCarroDTO> modelosCarros = modeloCarroService.findAll();
        return ResponseEntity.ok(modelosCarros);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um modelo de carro existente", description = "Atualiza as informações do modelo de carro com o ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modelo de carro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Modelo de carro não encontrado com o ID fornecido"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<ModeloCarroDTO> update(
            @Parameter(description = "ID do modelo de carro a ser atualizado", required = true)
            @PathVariable Long id,
            @Parameter(description = "Dados atualizados do modelo de carro", required = true)
            @Valid @RequestBody ModeloCarroDTO modeloCarroDTO) {
        try {
            ModeloCarroDTO updatedModeloCarroDTO = modeloCarroService.update(id, modeloCarroDTO);
            return ResponseEntity.ok(updatedModeloCarroDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null); // Customize as needed
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um modelo de carro pelo ID", description = "Remove o modelo de carro com o ID fornecido da locadora")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Modelo de carro removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Modelo de carro não encontrado com o ID fornecido")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do modelo de carro a ser removido", required = true)
            @PathVariable Long id) {
        if (modeloCarroService.findById(id) != null) {
            modeloCarroService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
