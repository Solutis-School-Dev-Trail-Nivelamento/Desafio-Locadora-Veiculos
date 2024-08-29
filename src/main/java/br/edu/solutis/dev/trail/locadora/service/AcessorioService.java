package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.BusinessException;
import br.edu.solutis.dev.trail.locadora.model.entity.Acessorio;
import br.edu.solutis.dev.trail.locadora.repository.AcessorioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AcessorioService {

    private static final Logger logger = LoggerFactory.getLogger(AcessorioService.class);

    @Autowired
    private AcessorioRepository acessorioRepository;

    @Transactional
    public void salvarAcessorio(Acessorio acessorio){
        logger.info("Cadastrando novo acessório.");

        validarAcessorio(acessorio);
        verificarExistencia(acessorio);

        Acessorio acessorioCadastrado = acessorioRepository.save(acessorio);
        logger.info("Acessório cadastrado com sucesso ID: {}", acessorioCadastrado.getId());
    }

    private void validarAcessorio(Acessorio acessorio) {
        validarCampoNaoNulo(acessorio.getDescricao());
    }

    private void validarCampoNaoNulo(String campo) {
        if (campo == null || campo.isEmpty()) {
            String mensagem = String.format("O campo %s não pode ser nulo.", "descrição");
            logger.error(mensagem);
            throw new BusinessException(mensagem);
        }
    }

    private void verificarExistencia(Acessorio acessorio) {
        if (acessorioRepository.existsByDescricao(acessorio.getDescricao())) {
            logger.error("Descrição já cadastrada");
            throw new BusinessException("Descrição já cadastrada");
        }
    }
}