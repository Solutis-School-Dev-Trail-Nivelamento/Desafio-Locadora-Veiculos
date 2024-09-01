package br.edu.solutis.dev.trail.locadora.controller;

import br.edu.solutis.dev.trail.locadora.model.dto.FabricanteDTO;
import br.edu.solutis.dev.trail.locadora.service.FabricanteService;
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
@RequestMapping("/api/fabricantes")
@RequiredArgsConstructor
@Validated
@Tag (name = "Fabricante")
public class FabricanteController {

    private final FabricanteService fabricanteService;

    @GetMapping
    @Operation(summary = "Obtém todos os fabricantes", description = "Recupera a lista de todos os fabricantes disponíveis")
    @ApiResponse(responseCode = "200", description = "Lista de fabricantes recuperada com sucesso")
    public ResponseEntity<List<FabricanteDTO>> getAllFabricantes() {
        List<FabricanteDTO> fabricantes = fabricanteService.findAll();
        return ResponseEntity.ok(fabricantes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um fabricante pelo ID", description = "Recupera os detalhes de um fabricante usando o ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fabricante encontrado"),
            @ApiResponse(responseCode = "404", description = "Fabricante não encontrado com o ID fornecido")
    })
    public ResponseEntity<FabricanteDTO> getFabricanteById(
            @Parameter(description = "ID do fabricante a ser recuperado", required = true)
            @PathVariable Long id) {
        return fabricanteService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cria um novo fabricante", description = "Cria um novo fabricante com as informações fornecidas")
    @ApiResponse(responseCode = "201", description = "Fabricante criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    public ResponseEntity<FabricanteDTO> createFabricante(
            @Parameter(description = "Dados do fabricante a ser criado", required = true)
            @Valid @RequestBody FabricanteDTO fabricanteDTO) {
        try {
            FabricanteDTO createdFabricante = fabricanteService.save(fabricanteDTO);
            return ResponseEntity.status(201).body(createdFabricante);
        } catch (Exception e) {
            // Log the exception for debugging
            // logger.error("Erro ao criar fabricante", e);
            return ResponseEntity.badRequest().build(); // Return no body
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um fabricante existente", description = "Atualiza as informações do fabricante com o ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fabricante atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Fabricante não encontrado com o ID fornecido"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<FabricanteDTO> updateFabricante(
            @Parameter(description = "ID do fabricante a ser atualizado", required = true)
            @PathVariable Long id,
            @Parameter(description = "Dados atualizados do fabricante", required = true)
            @Valid @RequestBody FabricanteDTO fabricanteDTO) {
        if (!fabricanteService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        try {
            fabricanteDTO.setId(id);
            FabricanteDTO updatedFabricante = fabricanteService.save(fabricanteDTO);
            return ResponseEntity.ok(updatedFabricante);
        } catch (Exception e) {
            // Log the exception for debugging
            // logger.error("Erro ao atualizar fabricante", e);
            return ResponseEntity.badRequest().build(); // Return no body
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um fabricante pelo ID", description = "Remove o fabricante com o ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Fabricante removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Fabricante não encontrado com o ID fornecido")
    })
    public ResponseEntity<Void> deleteFabricante(
            @Parameter(description = "ID do fabricante a ser removido", required = true)
            @PathVariable Long id) {
        if (fabricanteService.findById(id).isPresent()) {
            fabricanteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
