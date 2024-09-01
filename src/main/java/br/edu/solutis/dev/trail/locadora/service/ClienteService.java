package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.BusinessException;
import br.edu.solutis.dev.trail.locadora.mappers.ClienteMapper;
import br.edu.solutis.dev.trail.locadora.model.dto.ClienteDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.Cliente;
import br.edu.solutis.dev.trail.locadora.model.entity.Sexo;
import br.edu.solutis.dev.trail.locadora.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Autowired
    private ClienteMapper clienteMapper;

    @Transactional
    public ClienteDTO salvarCliente(@Valid ClienteDTO clienteDTO) {
        try {
            logger.info("Cadastrando novo cliente.");
            Cliente cliente = clienteMapper.toEntity(clienteDTO);
            validarCliente(cliente);
            verificarExistencia(cliente);
            Cliente clienteCadastrado = clienteRepository.save(cliente);
            logger.info("Cliente cadastrado com sucesso ID: {}", clienteCadastrado.getId());
            return clienteMapper.toDto(clienteCadastrado);
        } catch (DataIntegrityViolationException e) {
            logger.error("Erro de integridade de dados ao cadastrar cliente: {}", e.getMessage());
            throw new DataIntegrityViolationException("Erro de integridade de dados: " + e.getMessage());
        } catch (BusinessException e) {
            logger.error("Erro de negócio ao cadastrar cliente: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Erro inesperado ao cadastrar cliente: {}", e.getMessage());
            throw new RuntimeException("Erro inesperado ao cadastrar cliente", e);
        }
    }

    public List<ClienteDTO> obterTodos() {
        logger.info("Obtendo todos os clientes.");
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toDtoList(clientes);
    }


    public ClienteDTO obterPorId(Long id) {
        try {
            Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> {
                        logger.error("Cliente não encontrado ID: {}", id);
                        return new BusinessException("Cliente não encontrado");
                    });
            return clienteMapper.toDto(cliente);
        } catch (BusinessException e) {
            logger.error("Erro de negócio ao obter cliente por ID: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Erro inesperado ao obter cliente por ID: {}", e.getMessage());
            throw new RuntimeException("Erro inesperado ao obter cliente por ID", e);
        }
    }

    @Transactional
    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteNovoDTO) {
        try {
            Cliente clienteExistente = clienteRepository.findById(id)
                    .orElseThrow(() -> {
                        logger.error("Cliente não encontrado ID: {}", id);
                        return new BusinessException("Cliente não encontrado");
                    });
            Cliente clienteNovo = clienteMapper.toEntity(clienteNovoDTO);
            atualizarDados(clienteExistente, clienteNovo);
            validarCliente(clienteExistente);
            Cliente clienteAtualizado = clienteRepository.save(clienteExistente);
            logger.info("Cliente atualizado com sucesso ID: {}", clienteAtualizado.getId());
            return clienteMapper.toDto(clienteAtualizado);
        } catch (DataIntegrityViolationException e) {
            logger.error("Erro de integridade de dados ao atualizar cliente: {}", e.getMessage());
            throw new DataIntegrityViolationException("Erro de integridade de dados: " + e.getMessage());
        } catch (BusinessException e) {
            logger.error("Erro de negócio ao atualizar cliente: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Erro inesperado ao atualizar cliente: {}", e.getMessage());
            throw new RuntimeException("Erro inesperado ao atualizar cliente", e);
        }
    }


    public void excluirCliente(Long id) {
        try {
            logger.info("Excluindo cliente por ID.");
            clienteRepository.deleteById(id);
            logger.info("Cliente excluido com sucesso ID: {}", id);
        } catch (Exception e) {
            logger.error("Erro inesperado ao excluir cliente: {}", e.getMessage());
            throw new RuntimeException("Erro inesperado ao excluir cliente", e);
        }
    }

    private void atualizarDados(Cliente clienteExistente, Cliente clienteNovoDTO) {
        clienteExistente.setNome(clienteNovoDTO.getNome());
        clienteExistente.setEmail(clienteNovoDTO.getEmail());
        clienteExistente.setDataNascimento(clienteNovoDTO.getDataNascimento());
        clienteExistente.setSexo((clienteNovoDTO.getSexo()));
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
            throw new DataIntegrityViolationException("Email já cadastrado");
        }
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new DataIntegrityViolationException("CPF já cadastrado");
        }
        if (clienteRepository.existsByCnh(cliente.getCnh())) {
            throw new DataIntegrityViolationException("CNH já cadastrada");
        }
    }
}
