package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.model.entity.Pessoa;
import br.edu.solutis.dev.trail.locadora.repository.PessoaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public abstract class PessoaService<T extends Pessoa> {

    private static final Logger logger = LoggerFactory.getLogger(PessoaService.class);

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public T salvar(T pessoa) {
        logger.info("Cadastrando pessoa.");
        return pessoaRepository.save(pessoa);
    }
}