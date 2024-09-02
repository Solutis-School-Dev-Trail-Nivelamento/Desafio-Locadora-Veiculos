package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.NotFoundException;
import br.edu.solutis.dev.trail.locadora.model.entity.*;
import br.edu.solutis.dev.trail.locadora.repository.AluguelRepository;
import br.edu.solutis.dev.trail.locadora.repository.CarrinhoRepository;
import br.edu.solutis.dev.trail.locadora.repository.CarroRepository;
import br.edu.solutis.dev.trail.locadora.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    private static final Logger logger = LoggerFactory.getLogger(AluguelService.class);

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Transactional
    public Aluguel criarAluguel(Long clienteID, Long carroID, LocalDate dataEntrega, LocalDate dataDevolucao) {
        try {
            logger.info("Buscando cliente com ID: {}", clienteID);
            Cliente cliente = clienteRepository.findById(clienteID)
                    .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

            logger.info("Buscando carro com ID: {}", carroID);
            Carro carroSelecionado = carroRepository.findById(carroID)
                    .orElseThrow(() -> new NotFoundException("Carro não encontrado"));

            logger.info("Verificando disponibilidade do carro");
            if (!carroDisponivel(carroSelecionado.getId(), dataEntrega, dataDevolucao)) {
                throw new NotFoundException("Carro não disponível para o período selecionado");
            }

            logger.info("Obtendo ou criando carrinho para o cliente com ID: {}", clienteID);
            Carrinho carrinho = obterCarrinho(clienteID);

            Aluguel aluguel = new Aluguel(cliente, carroSelecionado, dataEntrega, dataDevolucao);
            aluguel.setDataPedido(LocalDateTime.now());
            aluguel.setStatus(AluguelStatus.PENDENTE);
            aluguel.setCarrinho(carrinho);
            aluguel.setValorTotal(aluguel.calcularValorTotal());
            aluguel.setQuantidadeDias(aluguel.calcularQuantidadeDias());

            aluguelRepository.save(aluguel);
            logger.info("Aluguel cadastrado com sucesso ID: {}", aluguel.getID());
            return aluguel;
        } catch (Exception e) {
            logger.error("Erro ao adicionar um aluguel: {}", e.getMessage());
            throw new RuntimeException("Erro inesperado ao adicionar um aluguel", e);
        }
    }

    public Carrinho obterCarrinho(Long clienteID) {
        logger.info("Obtendo carrinho para o cliente com ID: {}", clienteID);
        return carrinhoRepository.findByClienteId(clienteID)
                .orElseGet(() -> {
                    logger.info("Carrinho não encontrado, criando um novo carrinho para o cliente com ID: {}", clienteID);
                    Cliente cliente = clienteRepository.findById(clienteID)
                            .orElseThrow(() -> new NotFoundException("Cliente não encontrado com ID: " + clienteID));
                    Carrinho novoCarrinho = new Carrinho();
                    novoCarrinho.setCliente(cliente);
                    novoCarrinho.setDataCriacao(LocalDate.now());
                    return carrinhoRepository.save(novoCarrinho);
                });
    }


    public boolean carroDisponivel(Long carroID, LocalDate dataEntrega, LocalDate dataDevolucao) {
        logger.info("Verificando disponibilidade do carro com ID: {} para o período de {} a {}", carroID, dataEntrega, dataDevolucao);
        boolean disponivel = aluguelRepository.findConflictingAlugueis(carroID, dataEntrega, dataDevolucao).isEmpty();
        logger.info("Carro disponível: {}", disponivel);
        return disponivel;
    }


    @Transactional(readOnly = true)
    public List<Aluguel> obterTodos() {
        return this.aluguelRepository.findAll();
    }

    @Transactional
    public Aluguel atualizarAluguel(Long aluguelId, LocalDate dataEntrega, LocalDate dataDevolucao) {
        Aluguel aluguel = aluguelRepository.findById(aluguelId)
                .orElseThrow(() -> new EntityNotFoundException("Aluguel não encontrado"));

        aluguel.setDataEntrega(dataEntrega);
        aluguel.setDataDevolucao(dataDevolucao);
        aluguel.setValorTotal(aluguel.calcularValorTotal());
        aluguel.setQuantidadeDias(aluguel.calcularQuantidadeDias());

        return aluguelRepository.save(aluguel);
    }

    @Transactional
    public void deletarAluguel(Long aluguelId) {
        Aluguel aluguel = aluguelRepository.findById(aluguelId)
                .orElseThrow(() -> new EntityNotFoundException("Aluguel não encontrado"));

        aluguelRepository.delete(aluguel);
    }

    public List<Aluguel> buscarAlugueisConfirmados(Long clienteId) {
        return aluguelRepository.findByClienteIdAndStatus(clienteId, AluguelStatus.CONFIRMADO);
    }
}
