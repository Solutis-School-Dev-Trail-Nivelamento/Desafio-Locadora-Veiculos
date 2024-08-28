package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.BusinessException;
import br.edu.solutis.dev.trail.locadora.model.entity.Motorista;
import br.edu.solutis.dev.trail.locadora.repository.MotoristaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MotoristaService extends PessoaService<Motorista> {

    private static final Logger logger = LoggerFactory.getLogger(MotoristaService.class);

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Transactional
    public void salvar(Motorista motorista) {

        logger.info("Cadastrando novo motorista.");

        // Verificações antes de salvar o motorista
        validarMotorista(motorista);
        verificarExistencia(motorista);

        // Se todas as validações passarem, salva o motorista
        Motorista motoristaCadastrado = motoristaRepository.save(motorista);
        logger.info("Motorista cadastrado com sucesso ID: {}", motoristaCadastrado.getId());

    }

    private void validarMotorista(Motorista motorista) {
        validarCampoNaoNulo(motorista.getCnh());
    }

    private void validarCampoNaoNulo(String campo) {
        if (campo == null || campo.isEmpty()) {
            String mensagem = String.format("O campo %s não pode ser nulo.", "cnh");
            logger.error(mensagem);
            throw new BusinessException(mensagem);
        }
    }

    private void verificarExistencia(Motorista motorista) {
        if (motoristaRepository.existsByCnh(motorista.getCnh())) {
            logger.error("CNH já cadastrada");
            throw new BusinessException("CNH já cadastrada");
        }
    }
}
