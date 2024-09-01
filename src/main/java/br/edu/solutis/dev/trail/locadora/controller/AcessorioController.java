package br.edu.solutis.dev.trail.locadora.controller;

import br.edu.solutis.dev.trail.locadora.model.dto.AcessorioDTO;
import br.edu.solutis.dev.trail.locadora.service.AcessorioService;
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
@RequestMapping("/api/acessorios")
@RequiredArgsConstructor
@Validated
@Tag(name = "Acessorio")
public class AcessorioController {

    private final AcessorioService acessorioService;

    @GetMapping
    @Operation(summary = "Obtém todos os acessórios", description = "Recupera a lista de todos os acessórios disponíveis")
    @ApiResponse(responseCode = "200", description = "Lista de acessórios recuperada com sucesso")
    public ResponseEntity<List<AcessorioDTO>> getAllAcessorios() {
        List<AcessorioDTO> acessorios = acessorioService.findAll();
        return ResponseEntity.ok(acessorios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um acessório pelo ID", description = "Recupera os detalhes de um acessório usando o ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acessório encontrado"),
            @ApiResponse(responseCode = "404", description = "Acessório não encontrado com o ID fornecido")
    })
    public ResponseEntity<AcessorioDTO> getAcessorioById(
            @Parameter(description = "ID do acessório a ser recuperado", required = true)
            @PathVariable Long id) {
        return acessorioService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cria um novo acessório", description = "Cria um novo acessório com as informações fornecidas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Acessório criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<AcessorioDTO> createAcessorio(
            @Parameter(description = "Dados do acessório a ser criado", required = true)
            @Valid @RequestBody AcessorioDTO acessorioDTO) {
        try {
            AcessorioDTO createdAcessorio = acessorioService.save(acessorioDTO);
            return ResponseEntity.status(201).body(createdAcessorio);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null); // Customize as needed
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um acessório existente", description = "Atualiza as informações do acessório com o ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acessório atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Acessório não encontrado com o ID fornecido"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<AcessorioDTO> updateAcessorio(
            @Parameter(description = "ID do acessório a ser atualizado", required = true)
            @PathVariable Long id,
            @Parameter(description = "Dados atualizados do acessório", required = true)
            @Valid @RequestBody AcessorioDTO acessorioDTO) {
        return acessorioService.findById(id)
                .map(existingAcessorio -> {
                    acessorioDTO.setId(id);
                    AcessorioDTO updatedAcessorio = acessorioService.save(acessorioDTO);
                    return ResponseEntity.ok(updatedAcessorio);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um acessório pelo ID", description = "Remove o acessório com o ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Acessório removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Acessório não encontrado com o ID fornecido")
    })
    public ResponseEntity<Void> deleteAcessorio(
            @Parameter(description = "ID do acessório a ser removido", required = true)
            @PathVariable Long id) {
        if (acessorioService.findById(id).isPresent()) {
            acessorioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
