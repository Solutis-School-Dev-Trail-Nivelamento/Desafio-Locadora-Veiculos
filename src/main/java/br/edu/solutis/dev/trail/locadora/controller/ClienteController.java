package br.edu.solutis.dev.trail.locadora.controller;

import br.edu.solutis.dev.trail.locadora.mappers.ClienteMapper;
import br.edu.solutis.dev.trail.locadora.model.dto.ClienteDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.Aluguel;
import br.edu.solutis.dev.trail.locadora.service.ClienteService;
import br.edu.solutis.dev.trail.locadora.model.entity.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteMapper clienteMapper;


    @PostMapping
    @Operation(summary = "Cadastrando novo cliente", description = "Cadastrando novo cliente e retornando os dados criados")
    public ResponseEntity<ClienteDTO> cadastrar(@RequestBody ClienteDTO clienteDTO) {
        // Converte o DTO em entidade
        Cliente cliente = clienteMapper.toEntity(clienteDTO);

        // Salva o cliente usando o serviço
        Cliente clienteSalvo = clienteService.salvarCliente(cliente);

        // Converte a entidade salva de volta para DTO para retornar na resposta
        ClienteDTO clienteResposta = clienteMapper.toDto(clienteSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResposta);
    }

    @GetMapping("/cliente/{id}")
    @Operation(summary = "Obtendo cliente pelo ID", description = "Obtendo cliente pelo ID e retornando os dados")
    public ResponseEntity<Cliente> obterCliente(@PathVariable Long id){
        Optional<Cliente> cliente = clienteService.obterPorId(id);
        return cliente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
            description = "Atualiza um cliente e retorna as informações atualizadas",
            summary = "Atualiza as informações de um cliente")
    @PutMapping("/cliente/{id}")
    public ResponseEntity<ClienteDTO> atualizaCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {

        // Converte o DTO em entidade
        Cliente cliente = clienteMapper.toEntity(clienteDTO);

        // Salva o cliente usando o serviço
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);

        // Converte a entidade salva de volta para DTO para retornar na resposta
        ClienteDTO clienteResposta = clienteMapper.toDto(clienteAtualizado);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResposta);
    }

    @PostMapping("/{clienteId}/alugueis")
    @Operation(summary = "Adicionando alugueis", description = "Adicionando alugueis para o cliente")
    public ResponseEntity<Void> adicionarAluguel(@PathVariable Long clienteId, @RequestBody Aluguel aluguel) {
        clienteService.adicionarAluguel(clienteId, aluguel);
        return ResponseEntity.ok().build();
    }
}
