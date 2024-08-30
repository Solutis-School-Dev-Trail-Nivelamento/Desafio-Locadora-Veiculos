package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.BusinessException;
import br.edu.solutis.dev.trail.locadora.model.entity.Cliente;
import br.edu.solutis.dev.trail.locadora.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvarCliente(Cliente cliente) {
        logger.info("Cadastrando novo cliente.");
        validarCliente(cliente);
        verificarExistencia(cliente);
        Cliente clienteCadastrado = clienteRepository.save(cliente);
        logger.info("Cliente cadastrado com sucesso ID: {}", clienteCadastrado.getId());
        return clienteCadastrado;
    }

    public List<Cliente> obterTodos() {
        logger.info("Obtendo todos os clientes.");
        return clienteRepository.findAll();
    }

    public Cliente obterPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente atualizarCliente(Long id, Cliente clienteNovo) {
        Cliente clienteExistente = obterPorId(id);
        atualizarDados(clienteExistente, clienteNovo);
        validarCliente(clienteExistente);
        Cliente clienteAtualizado = clienteRepository.save(clienteExistente);
        logger.info("Cliente atualizado com sucesso ID: {}", clienteAtualizado.getId());
        return clienteAtualizado;
    }

    public void excluirCliente(Long id) {
        logger.info("Excluindo cliente por ID.");
        clienteRepository.deleteById(id);
    }

    private void atualizarDados(Cliente clienteExistente, Cliente clienteNovo) {
        clienteExistente.setNome(clienteNovo.getNome());
        clienteExistente.setCpf(clienteNovo.getCpf());
        clienteExistente.setEmail(clienteNovo.getEmail());
        clienteExistente.setDataNascimento(clienteNovo.getDataNascimento());
        clienteExistente.setSexo(clienteNovo.getSexo());
        clienteExistente.setCnh(clienteNovo.getCnh());
    }

    private void validarCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new BusinessException("O campo nome não pode ser nulo.");
        }
        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
            throw new BusinessException("O campo email não pode ser nulo.");
        }
        if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
            throw new BusinessException("O campo CPF não pode ser nulo.");
        }
        if (cliente.getDataNascimento() == null || cliente.getDataNascimento().isAfter(LocalDate.now())) {
            throw new BusinessException("Data de nascimento inválida.");
        }
        if (Period.between(cliente.getDataNascimento(), LocalDate.now()).getYears() < 18) {
            throw new BusinessException("A pessoa deve ter pelo menos 18 anos.");
        }
        if (cliente.getCnh() == null || cliente.getCnh().isEmpty()) {
            throw new BusinessException("O campo CNH não pode ser nulo.");
        }
    }

    private void verificarExistencia(Cliente cliente) {
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new BusinessException("Email já cadastrado");
        }
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new BusinessException("CPF já cadastrado");
        }
        if (clienteRepository.existsByCnh(cliente.getCnh())) {
            throw new BusinessException("CNH já cadastrada");
        }
    }
}
