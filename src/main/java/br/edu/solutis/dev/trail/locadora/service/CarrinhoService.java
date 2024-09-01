package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.NotFoundException;
import br.edu.solutis.dev.trail.locadora.model.entity.*;
import br.edu.solutis.dev.trail.locadora.repository.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Service
public class CarrinhoService {

    private static final Logger logger = LoggerFactory.getLogger(CarrinhoService.class);

    @Autowired
    private CarroRepository carroRepository;
    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private AluguelRepository aluguelRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    /*@Autowired
    private EmailService emailService;*/

    @Transactional
    public Carrinho criarCarrinho() {
        logger.info("Criando novo carrinho de aluguel.");
        Carrinho carrinho = new Carrinho();
        carrinho.setDataCriacao(LocalDate.now());
        carrinho.setAlugueis(new ArrayList<>());
        return carrinhoRepository.save(carrinho);
    }

    @Transactional
    public Carrinho adicionarVeiculo(Long carrinhoId, Long veiculoId, Long clienteId, LocalDate dataInicio, LocalDate dataFim) {
        logger.info("Adicionando veículo ao carrinho. Carrinho ID: {}, Veículo ID: {}, Cliente ID: {}", carrinhoId, veiculoId, clienteId);

        Carrinho carrinho = carrinhoRepository.findById(carrinhoId).orElseThrow(() -> new NotFoundException("Carrinho não encontrado"));
        Carro carro = carroRepository.findById(veiculoId).orElseThrow(() -> new NotFoundException("Carro não disponível"));
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        // Verificar disponibilidade do veículo para o período selecionado
        if (!carroDisponivel(carro.getId(), dataInicio, dataFim)) {
            throw new NotFoundException("Carro não disponível para o período selecionado");
        }

        Aluguel aluguel = new Aluguel();
        aluguel.setCarro(carro);
        aluguel.setCliente(cliente);
        aluguel.setCarrinho(carrinho);
        aluguel.setDataPedido(LocalDateTime.now());
        aluguel.setDataEntrega(dataInicio);
        aluguel.setDataDevolucao(dataFim);
        aluguel.setQuantidadeDias(aluguel.calcularQuantidadeDias());
        aluguel.setValorTotal(aluguel.calcularValorTotal());

        carrinho.getAlugueis().add(aluguel);
        aluguelRepository.save(aluguel);
        return carrinhoRepository.save(carrinho);
    }

    @Transactional
    public Carrinho confirmarReserva(Long carrinhoId) {
        logger.info("Confirmando reserva do carrinho. Carrinho ID: {}", carrinhoId);

        Carrinho carrinho = carrinhoRepository.findById(carrinhoId).orElseThrow(() -> new NotFoundException("Carrinho não encontrado"));

        for (Aluguel aluguel : carrinho.getAlugueis()) {
            aluguel.setStatus(AluguelStatus.CONFIRMADO);
            aluguelRepository.save(aluguel);
            // Enviar e-mail de confirmação (simulado)
            enviarEmailConfirmacao(aluguel);
        }

        return carrinho;
    }

    @Transactional
    public boolean carroDisponivel(Long carroId, LocalDate dataInicio, LocalDate dataFim) {
        List<Aluguel> alugueisConflitantes = aluguelRepository.findConflictingAlugueis(carroId, dataInicio, dataFim);
        return alugueisConflitantes.isEmpty();
    }

    @Transactional
    public void enviarEmailConfirmacao(Aluguel aluguel) {
        /*String destinatario = aluguel.getCliente().getEmail();
        String assunto = "Confirmação de Reserva de Aluguel";
        String mensagem = String.format("Olá %s,\n\nSua reserva para o veículo %s foi confirmada.\n\nDetalhes do Aluguel:\nData de Entrega: %s\nData de Devolução: %s\nValor Total: %s\n\nObrigado por escolher nossa locadora!",
                aluguel.getCliente().getNome(),
                aluguel.getCarro().getModelo().getDescricao(),
                aluguel.getDataEntrega(),
                aluguel.getDataDevolucao(),
                aluguel.getValorTotal());

        emailService.enviarEmailConfirmacao(destinatario, assunto, mensagem);*/
        logger.info("Enviando e-mail de confirmação para o aluguel ID: {}", aluguel.getID());
    }
}
