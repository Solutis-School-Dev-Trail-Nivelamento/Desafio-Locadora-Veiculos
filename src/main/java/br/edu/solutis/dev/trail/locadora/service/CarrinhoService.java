package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.model.entity.*;
import br.edu.solutis.dev.trail.locadora.repository.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
@Service
public class CarrinhoService {
    @Autowired
    private CarroRepository carroRepository;
    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private AluguelRepository aluguelRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public Carrinho criarCarrinho() {
        Carrinho carrinho = new Carrinho();
        carrinho.setDataCriacao(LocalDateTime.now());
        carrinho.setAlugueis(new ArrayList<>());
        return carrinhoRepository.save(carrinho);
    }

  /*  public Aluguel adicionarCarro(Long carrinhoId, Long veiculoId, Long clienteId, LocalDate dataInicio, LocalDate dataFim) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId).orElseThrow();
        Carro carro = carroRepository.findById(veiculoId).orElseThrow();
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow();

        Aluguel aluguel = new Aluguel();
        aluguel.setCarro(carro);
        aluguel.setCliente(cliente);
        aluguel.setCarrinho(carrinho);
        aluguel.setDataPedido(LocalDate.now());
        aluguel.setDataEntrega(dataInicio);
        aluguel.setDataDevolucao(dataFim);
        aluguel.setQuantidadeDias(aluguel.calcularQuantidadeDias());
        aluguel.setValorTotal(aluguel.calcularValorTotal());

        carrinho.getAlugueis().add(aluguel);
        aluguelRepository.save(aluguel);
        return carrinhoRepository.save(carrinho);
    }*/

    public Carrinho confirmarReserva(Long carrinhoId) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId).orElseThrow();
        // Lógica para confirmar a reserva, como enviar e-mail de confirmação, etc.
        return carrinho;
    }
}
