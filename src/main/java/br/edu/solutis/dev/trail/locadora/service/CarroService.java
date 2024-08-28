package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.BusinessException;
import br.edu.solutis.dev.trail.locadora.model.entity.Carro;
import br.edu.solutis.dev.trail.locadora.repository.CarroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarroService {

    private static final Logger logger = LoggerFactory.getLogger(CarroService.class);

    @Autowired
    private CarroRepository carroRepository;

    @Transactional
    public void salvarCarro(Carro carro) {
        logger.info("Cadastrando novo carro.");

        validarCarro(carro);
        verificarExistencia(carro);

        Carro carroCadastrado = carroRepository.save(carro);
        logger.info("Carro cadastrado com sucesso ID: {}", carroCadastrado.getId());
    }

    private void validarCarro(Carro carro) {
        validarCampoNaoNulo(carro.getPlaca(), "placa");
        validarCampoNaoNulo(carro.getModelo().getDescricao(), "modelo");
    }

    private void validarCampoNaoNulo(String campo, String nomeCampo) {
        if (campo == null || campo.isEmpty()) {
            String mensagem = String.format("O campo %s não pode ser nulo.", nomeCampo);
            logger.error(mensagem);
            throw new BusinessException(mensagem);
        }
    }

    private void verificarExistencia(Carro carro) {
        if (carroRepository.existsByPlaca(carro.getPlaca())) {
            logger.error("Placa já cadastrada");
            throw new BusinessException("Placa já cadastrada");
        }
    }
}
