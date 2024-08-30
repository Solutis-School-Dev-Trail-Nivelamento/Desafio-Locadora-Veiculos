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

import java.util.List;

@RestController
@RequestMapping("/")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteMapper clienteMapper;

    @PostMapping("/cliente")
    @Operation(summary = "Cadastra um novo cliente", description = "Cadastra um novo cliente e retorna os dados criados")
    public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente clienteSalvo = clienteService.salvarCliente(cliente);
        ClienteDTO clienteResposta = clienteMapper.toDto(clienteSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResposta);
    }

    @GetMapping("/clientes")
    @Operation(summary = "Obtem todos clientes", description = "Retorna a informação com todos clientes cadastrados")
    public ResponseEntity<List<ClienteDTO>> obterTodosClientes() {
        List<Cliente> clientes = clienteService.obterTodos();
        List<ClienteDTO> clienteDTOs = clienteMapper.toDtoList(clientes);
        return new ResponseEntity<>(clienteDTOs, HttpStatus.OK);
    }

    @GetMapping("/cliente/{id}")
    @Operation(summary = "Obtém cliente por ID", description = "Retorna cliente pelo ID")
    public ResponseEntity<ClienteDTO> obterCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.obterPorId(id);
        ClienteDTO clienteResposta = clienteMapper.toDto(cliente);
        return ResponseEntity.ok(clienteResposta);
    }

    @PutMapping("/cliente/{id}")
    @Operation(summary = "Atualiza cliente", description = "Atualiza as informações de um cliente")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        ClienteDTO clienteResposta = clienteMapper.toDto(clienteAtualizado);
        return ResponseEntity.ok(clienteResposta);
    }

    @DeleteMapping("cliente/{id}")
    @Operation(summary = "Deleta cliente", description = "Deleta cliente pelo ID")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }

    /*@GetMapping("cliente/{id}/alugueis")
    @Operation(summary = "Exibe os aluguéis do usuário", description = "Exibe ao usuário todos os aluguéis relacionados ao seu id")
    public ResponseEntity<List<Aluguel>> alugueisConfirmados(@PathVariable Long id) {
        List<Aluguel> alugueis =  clienteService.alugueisConfirmados(id);
        return ResponseEntity.ok(alugueis);
    }*/
}
