package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.BusinessException;
import br.edu.solutis.dev.trail.locadora.model.entity.Pessoa;
import br.edu.solutis.dev.trail.locadora.repository.PessoaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

@Service
public abstract class PessoaService<T extends Pessoa> {

    private static final Logger logger = LoggerFactory.getLogger(PessoaService.class);

    @Autowired
    private PessoaRepository pessoaRepository;

    /*A anotação @Transactional está corretamente aplicada ao método salvarMotorista,
    garantindo que todas as operações de banco de dados sejam executadas em uma única transação.
    Se ocorrer qualquer exceção dentro do método, todas as alterações serão revertidas.*/
    @Transactional
    public void salvarPessoa(T pessoa) {
        validarPessoa(pessoa);
        verificarExistencia(pessoa);
        pessoaRepository.save(pessoa);
    }

    private void validarPessoa(T pessoa) {
        validarCampoNaoNulo(pessoa.getNome(), "nome");
        validarCampoNaoNulo(pessoa.getEmail(), "email");
        validarCampoNaoNulo(pessoa.getCpf(), "cpf");
        validarDataNascimento(pessoa.getDataNascimento());
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

    private void verificarExistencia(Pessoa pessoa) {
        if (pessoaRepository.existsByEmail(pessoa.getEmail())) {
            logger.error("Email já cadastrado");
            throw new BusinessException("Email já cadastrado");
        }
        if (pessoaRepository.existsByCpf(pessoa.getCpf())) {
            logger.error("CPF já cadastrado");
            throw new BusinessException("CPF já cadastrado");
        }
    }
}
