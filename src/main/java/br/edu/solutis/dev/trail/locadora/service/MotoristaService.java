package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.BusinessException;
import br.edu.solutis.dev.trail.locadora.model.Motorista;
import br.edu.solutis.dev.trail.locadora.repository.MotoristaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

@Service
public class MotoristaService {

    private static final Logger logger = LoggerFactory.getLogger(MotoristaService.class);

    private final MotoristaRepository motoristaRepository;

    @Autowired
    public MotoristaService(MotoristaRepository motoristaRepository) {
        this.motoristaRepository = motoristaRepository;
    }

    /*A anotação @Transactional está corretamente aplicada ao método salvarMotorista,
    garantindo que todas as operações de banco de dados sejam executadas em uma única transação.
    Se ocorrer qualquer exceção dentro do método, todas as alterações serão revertidas.*/
    @Transactional
    public void salvarMotorista(Motorista motorista) {

        logger.info("Cadastrando novo cliente.");

        // Verificações antes de salvar o motorista
        validarCliente(motorista);
        verificarExistencia(motorista);

        // Se todas as validações passarem, salva o motorista
        Motorista motoristaCadastrado = motoristaRepository.save(motorista);
        logger.info("Cliente criado com sucesso ID: {}", motoristaCadastrado.getId());

    }

    private void validarCliente(Motorista motorista) {
        validarCampoNaoNulo(motorista.getNome(), "nome");
        validarCampoNaoNulo(motorista.getEmail(), "email");
        validarCampoNaoNulo(motorista.getCpf(), "cpf");
        validarCampoNaoNulo(motorista.getCnh(), "cnh");
        validarDataNascimento(motorista.getDataNascimento());
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
            String mensagem = "O motorista deve ter pelo menos 18 anos.";
            logger.error(mensagem);
            throw new BusinessException(mensagem);
        }
    }

    private void verificarExistencia(Motorista motorista) {
        if (motoristaRepository.existsByEmail(motorista.getEmail())) {
            logger.error("Email já cadastrado");
            throw new BusinessException("Email já cadastrado");
        }
        if (motoristaRepository.existsByCpf(motorista.getCpf())) {
            logger.error("CPF já cadastrado");
            throw new BusinessException("CPF já cadastrado");
        }
        if (motoristaRepository.existsByCnh(motorista.getCnh())) {
            logger.error("CNH já cadastrada");
            throw new BusinessException("CNH já cadastrada");
        }
    }
}
