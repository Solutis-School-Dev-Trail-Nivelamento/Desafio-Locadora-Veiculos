package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.NotFoundException;
import br.edu.solutis.dev.trail.locadora.model.entity.Aluguel;
import br.edu.solutis.dev.trail.locadora.model.entity.AluguelStatus;
import br.edu.solutis.dev.trail.locadora.model.entity.Carro;
import br.edu.solutis.dev.trail.locadora.model.entity.Cliente;
import br.edu.solutis.dev.trail.locadora.repository.AluguelRepository;
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
import java.util.List;

@Service
public class AluguelService {

    private static final Logger logger = LoggerFactory.getLogger(AluguelService.class);

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Valid
    @Transactional
    public Aluguel criarAluguel(Long clienteID, Long carroID, LocalDate dataEntrega, LocalDate dataDevolucao) {
        Cliente cliente = clienteRepository.findById(clienteID)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
        Carro carroSelecionado = carroRepository.findById(carroID)
                .orElseThrow(() -> new NotFoundException("Carro não encontrado."));

        Aluguel aluguel = new Aluguel(cliente, carroSelecionado, dataEntrega, dataDevolucao);
        aluguel.setValorTotal(aluguel.calcularValorTotal());
        aluguel.setQuantidadeDias(aluguel.calcularQuantidadeDias());
        aluguelRepository.save(aluguel);

        logger.info("Aluguel cadastrado com sucesso ID: {}", aluguel.getID());
        return aluguel;
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
