package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.NotFoundException;
import br.edu.solutis.dev.trail.locadora.model.entity.*;
import br.edu.solutis.dev.trail.locadora.repository.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CarrinhoService {

    private static final Logger logger = LoggerFactory.getLogger(CarrinhoService.class);

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private AluguelRepository aluguelRepository;

    @Transactional
    public Carrinho adicionarAluguelExistente(Long carrinhoId, Long aluguelId) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId).orElseThrow(() -> new NotFoundException("Carrinho não encontrado"));
        Aluguel aluguel = aluguelRepository.findById(aluguelId).orElseThrow(() -> new NotFoundException("Aluguel não encontrado"));

        aluguel.setCarrinho(carrinho);
        carrinho.getAlugueis().add(aluguel);

        aluguelRepository.save(aluguel);
        return carrinhoRepository.save(carrinho);
    }
    @Transactional
    public Carrinho obterCarrinho(Long carrinhoId) {
        return carrinhoRepository.findById(carrinhoId).orElseThrow(() -> new NotFoundException("Carrinho não encontrado"));
    }


    @Transactional
    public Carrinho confirmarReserva(Long carrinhoId) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId).orElseThrow(() -> new NotFoundException("Carrinho não encontrado"));

        for (Aluguel aluguel : carrinho.getAlugueis()) {
            aluguel.setQuantidadeDias(aluguel.calcularQuantidadeDias());
            aluguel.setValorTotal(aluguel.calcularValorTotal());
            aluguel.setStatus(AluguelStatus.CONFIRMADO);
            aluguelRepository.save(aluguel);
            enviarEmailConfirmacao(aluguel);
        }
        return carrinho;
    }

   @Transactional
    public Aluguel atualizarAluguel(Long aluguelId, LocalDate dataEntrega, LocalDate dataDevolucao) {
        Aluguel aluguel = aluguelRepository.findById(aluguelId).orElseThrow(() -> new NotFoundException("Aluguel não encontrado"));

        aluguel.setDataEntrega(dataEntrega);
        aluguel.setDataDevolucao(dataDevolucao);
        aluguel.setQuantidadeDias(aluguel.calcularQuantidadeDias());
        aluguel.setValorTotal(aluguel.calcularValorTotal());
        aluguel.setStatus(AluguelStatus.PENDENTE);
        return aluguelRepository.save(aluguel);
    }

    @Transactional
    public void enviarEmailConfirmacao(Aluguel aluguel) {
        logger.info("Enviando e-mail de confirmação para o aluguel ID: {}", aluguel.getID());
    }
}
