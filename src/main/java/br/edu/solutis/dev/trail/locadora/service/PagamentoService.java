package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.NotFoundException;
import br.edu.solutis.dev.trail.locadora.mappers.PagamentoMapper;
import br.edu.solutis.dev.trail.locadora.model.dto.PagamentoDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.Aluguel;
import br.edu.solutis.dev.trail.locadora.model.entity.AluguelStatus;
import br.edu.solutis.dev.trail.locadora.model.entity.Carrinho;
import br.edu.solutis.dev.trail.locadora.model.entity.Pagamento;
import br.edu.solutis.dev.trail.locadora.repository.AluguelRepository;
import br.edu.solutis.dev.trail.locadora.repository.CarrinhoRepository;
import br.edu.solutis.dev.trail.locadora.repository.PagamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private PagamentoMapper pagamentoMapper;

    @Transactional
    public Pagamento iniciarPagamento(Long carrinhoId, PagamentoDTO pagamentoDTO) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new NotFoundException("Carrinho não encontrado"));

        BigDecimal valorTotal = carrinho.getAlugueis().stream()
                .map(Aluguel::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Pagamento pagamento = pagamentoMapper.toEntity(pagamentoDTO);
        pagamento.setCarrinhoId(carrinhoId);
        pagamento.setValorTotal(valorTotal);
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setStatus(AluguelStatus.PENDENTE);

        return pagamentoRepository.save(pagamento);
    }

    @Transactional
    public Pagamento confirmarPagamento(Long pagamentoId) {
        Pagamento pagamento = pagamentoRepository.findById(pagamentoId)
                .orElseThrow(() -> new NotFoundException("Pagamento não encontrado"));

        pagamento.setStatus(AluguelStatus.CONFIRMADO);
        pagamento.setDataPagamento(LocalDateTime.now());

        // Marcar o veículo como reservado
        Carrinho carrinho = carrinhoRepository.findById(pagamento.getCarrinhoId())
                .orElseThrow(() -> new NotFoundException("Carrinho não encontrado"));
        carrinho.getAlugueis().forEach(aluguel -> aluguel.setStatus(AluguelStatus.RESERVADO));
        carrinhoRepository.save(carrinho);

        return pagamentoRepository.save(pagamento);
    }
}
