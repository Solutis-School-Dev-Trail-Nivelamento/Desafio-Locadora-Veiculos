package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.BusinessException;
import br.edu.solutis.dev.trail.locadora.model.entity.Aluguel;
import br.edu.solutis.dev.trail.locadora.model.entity.Cliente;
import br.edu.solutis.dev.trail.locadora.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Valid
    @Transactional
    public Cliente salvarCliente(Cliente cliente) {

        logger.info("Cadastrando novo cliente.");

        // Verificações antes de salvar o motorista
        validarCliente(cliente);
        verificarExistencia(cliente);

        // Se todas as validações passarem, salva o motorista
        Cliente clienteCadastrado = clienteRepository.save(cliente);
        logger.info("Cliente cadastrado com sucesso ID: {}", clienteCadastrado.getId());
        return clienteCadastrado;
    }

    public Optional<Cliente> obterPorId(Long clienteId) {
        return clienteRepository.findById(clienteId);
    }

    @Transactional
    public Cliente atualizarCliente(Long id, Cliente clienteNovo) {
        Optional<Cliente> clienteExistenteOpt = clienteRepository.findById(id);

        if (clienteExistenteOpt.isPresent()) {
            Cliente clienteExistente = getCliente(clienteNovo, clienteExistenteOpt);

            // Realiza validações após a atualização
            validarCliente(clienteExistente);

            // Salva o cliente atualizado no repositório
            Cliente clienteAtualizado = clienteRepository.save(clienteExistente);
            logger.info("Cliente atualizado com sucesso ID: {}", clienteAtualizado.getId());
            return clienteAtualizado;
        } else {
            throw new BusinessException("Cliente não encontrado");
        }
    }

    private static Cliente getCliente(Cliente clienteNovo, Optional<Cliente> clienteExistenteOpt) {
        Cliente clienteExistente = clienteExistenteOpt.get();

        // Atualiza os dados do cliente existente com os novos dados fornecidos
        clienteExistente.setNome(clienteNovo.getNome());
        clienteExistente.setCpf(clienteNovo.getCpf());
        clienteExistente.setEmail(clienteNovo.getEmail());
        clienteExistente.setDataNascimento(clienteNovo.getDataNascimento());
        clienteExistente.setSexo(clienteNovo.getSexo());
        clienteExistente.setCnh(clienteNovo.getCnh());
        return clienteExistente;
    }

    @Transactional
    public void adicionarAluguel(Long clienteId, Aluguel aluguel) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        aluguel.setCliente(cliente); // Estabelece o relacionamento entre Aluguel e Cliente
        cliente.getAlugueis().add(aluguel); // Adiciona o aluguel à lista de alugueis do cliente

        clienteRepository.save(cliente); // Salva o cliente com o novo aluguel
    }

    private void validarCliente(Cliente cliente) {
        validarCampoNaoNulo(cliente.getNome(), "nome");
        validarCampoNaoNulo(cliente.getEmail(), "email");
        validarCampoNaoNulo(cliente.getCpf(), "cpf");
        validarDataNascimento(cliente.getDataNascimento());
        validarCampoNaoNulo(cliente.getCnh(), "cnh");
    }

    private void validarCampoNaoNulo(String campo, String nomeCampo) {
        if (campo == null || campo.isEmpty()) {
            String mensagem = String.format("O campo %s não pode ser nulo.", nomeCampo);
            logger.error(mensagem);
            throw new BusinessException(mensagem);
        }
    }

    private void validarDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            String mensagem = "O campo data de nascimento não pode ser nulo.";
            logger.error(mensagem);
            throw new BusinessException(mensagem);
        }

        if (dataNascimento.isAfter(LocalDate.now())) {
            String mensagem = "A data de nascimento não pode ser no futuro.";
            logger.error(mensagem);
            throw new BusinessException(mensagem);
        }

        if (Period.between(dataNascimento, LocalDate.now()).getYears() < 18) {
            String mensagem = "A pessoa deve ter pelo menos 18 anos.";
            logger.error(mensagem);
            throw new BusinessException(mensagem);
        }
    }

    private void verificarExistencia(Cliente cliente) {
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            logger.error("Email já cadastrado");
            throw new BusinessException("Email já cadastrado");
        }
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            logger.error("CPF já cadastrado");
            throw new BusinessException("CPF já cadastrado");
        }
        if (clienteRepository.existsByCnh(cliente.getCnh())) {
            logger.error("CNH já cadastrada");
            throw new BusinessException("CNH já cadastrada");
        }
    }
}