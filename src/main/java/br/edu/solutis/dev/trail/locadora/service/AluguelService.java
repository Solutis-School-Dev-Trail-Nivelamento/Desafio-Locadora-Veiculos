/* package br.edu.solutis.dev.trail.locadora.service;

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

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;

@Service
public class AluguelService{

    private static final Logger logger = LoggerFactory.getLogger(AluguelService.class);

    @Autowired
    private ClienteRepository AluguelRepository;

    @Valid
    @Transactional
    public Cliente salvarAluguel(Aluguel aluguel) {

        logger.info("Cadastrando novo Aluguel.");

        validarAluguel(aluguel);
        verificarExistencia(aluguel);

        Aluguel aluguelCadastrado = aluguelRepository.save(aluguel);
        logger.info("Aluguel cadastrado com sucesso ID: {}", aluguelCadastrado.getId());
        return aluguelCadastrado;
    }

    public Optional<Aluguel> obterPorId(Long aluguelId){
        return aluguelRepository.findById(aluguelId);
    }

    @Transactional
    public void adicionarAluguel(Long clienteId, Aluguel aluguel) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    */