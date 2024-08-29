package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.BusinessException;
import br.edu.solutis.dev.trail.locadora.model.entity.Cliente;
import br.edu.solutis.dev.trail.locadora.model.entity.Pessoa;
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
public class ClienteService extends PessoaService<Cliente> {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Valid
    @Transactional
    public Cliente salvar(Cliente cliente) {

        logger.info("Cadastrando novo cliente.");

        // Verificações antes de salvar o motorista
        validarMotorista(cliente);
        verificarExistencia(cliente);

        // Se todas as validações passarem, salva o motorista
        Cliente clienteCadastrado = clienteRepository.save(cliente);
        logger.info("Cliente cadastrado com sucesso ID: {}", clienteCadastrado.getId());
        return clienteCadastrado;
    }

    public Optional<Cliente> obterPorId(Long id){
        return clienteRepository.findById(id);
    }

    private void validarMotorista(Cliente cliente) {
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