package com.solutis.locadora.service;

import com.solutis.locadora.exceptions.BusinessException;
import com.solutis.locadora.model.Motorista;
import com.solutis.locadora.repository.MotoristaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MotoristaService {

    private static final Logger logger = LoggerFactory.getLogger(MotoristaService.class);

    private final MotoristaRepository motoristaRepository;

    @Autowired
    public MotoristaService(MotoristaRepository motoristaRepository) {
        this.motoristaRepository = motoristaRepository;
    }

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
        Optional.ofNullable(motorista.getNome()).orElseThrow(() -> {
            logger.error("O campo nome não pode ser nulo.");
            return new BusinessException("O campo nome não pode ser nulo.");
        });
        Optional.ofNullable(motorista.getEmail()).orElseThrow(() -> {
            logger.error("O campo email não pode ser nulo.");
            return new BusinessException("O campo email não pode ser nulo.");
        });
        Optional.ofNullable(motorista.getCpf()).orElseThrow(() -> {
            logger.error("O campo cpf não pode ser nulo.");
            return new BusinessException("O campo cpf não pode ser nulo.");
        });
        Optional.ofNullable(motorista.getCnh()).orElseThrow(() -> {
            logger.error("O campo cnh não pode ser nulo.");
            return new BusinessException("O campo cnh não pode ser nulo.");
        });
        Optional.ofNullable(motorista.getDataNascimento()).orElseThrow(() -> {
            logger.error("O campo data de nascimento não pode ser nulo.");
            return new BusinessException("O campo data de nascimento não pode ser nulo.");
        });
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
