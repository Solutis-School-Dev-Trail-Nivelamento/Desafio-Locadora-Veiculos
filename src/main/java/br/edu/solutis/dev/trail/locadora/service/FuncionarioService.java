package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.BusinessException;
import br.edu.solutis.dev.trail.locadora.model.entity.Funcionario;
import br.edu.solutis.dev.trail.locadora.repository.FuncionarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuncionarioService extends PessoaService<Funcionario>{

    private static final Logger logger = LoggerFactory.getLogger(FuncionarioService.class);

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public void salvarPessoa(Funcionario funcionario){
        logger.info("Cadastrando novo funcionario.");

        // Verificações antes de salvar o motorista
        validarFuncionario(funcionario);
        verificarExistencia(funcionario);

        // Se todas as validações passarem, salva o motorista
        Funcionario funcionarioCadastrado = funcionarioRepository.save(funcionario);
        logger.info("Motorista cadastrado com sucesso ID: {}", funcionarioCadastrado.getId());
    }

    private void validarFuncionario(Funcionario funcionario) {
        validarCampoNaoNulo(funcionario.getMatricula());
    }

    private void validarCampoNaoNulo(String campo) {
        if (campo == null || campo.isEmpty()) {
            String mensagem = String.format("O campo %s não pode ser nulo.", "matricula");
            logger.error(mensagem);
            throw new BusinessException(mensagem);
        }
    }

    private void verificarExistencia(Funcionario funcionario) {
        if (funcionarioRepository.existsByMatricula(funcionario.getMatricula())) {
            logger.error("Matrícula já cadastrada");
            throw new BusinessException("CNH já cadastrada");
        }
    }
}
