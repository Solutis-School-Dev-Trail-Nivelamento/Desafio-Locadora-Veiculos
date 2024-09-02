package br.edu.solutis.dev.trail.locadora.controller;

import br.edu.solutis.dev.trail.locadora.model.dto.ApoliceSeguroDTO;
import br.edu.solutis.dev.trail.locadora.service.ApoliceSeguroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/apolices")
public class ApoliceSeguroController {

    private final ApoliceSeguroService service;

    public ApoliceSeguroController(ApoliceSeguroService service) {
        this.service = service;
    }

    @PostMapping
    public ApoliceSeguroDTO criarApolice(@RequestBody ApoliceSeguroDTO dto) {
        return service.criarApolice(dto);
    }

    @GetMapping("/{id}")
    public ApoliceSeguroDTO obterApolicePorId(@PathVariable Long id) {
        return service.obterApolicePorId(id);
    }

    @GetMapping
    public List<ApoliceSeguroDTO> listarTodasApólices() {
        return service.listarTodasApólices();
    }

    @PutMapping("/{id}")
    public ApoliceSeguroDTO atualizarApolice(@PathVariable Long id, @RequestBody ApoliceSeguroDTO dto) {
        return service.atualizarApolice(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletarApolice(@PathVariable Long id) {
        service.deletarApolice(id);
    }
}
