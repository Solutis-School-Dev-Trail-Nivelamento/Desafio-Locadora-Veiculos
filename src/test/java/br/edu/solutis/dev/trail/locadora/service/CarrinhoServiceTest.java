/*package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.model.entity.*;
import br.edu.solutis.dev.trail.locadora.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CarrinhoServiceTest {

    @InjectMocks
    private CarrinhoService carrinhoService;

    @Mock
    private CarroRepository carroRepository;

    @Mock
    private CarrinhoRepository carrinhoRepository;

    @Mock
    private AluguelRepository aluguelRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAdicionarVeiculo() {
        // Configurar mocks
        Carrinho carrinho = new Carrinho();
        carrinho.setId(1L);
        when(carrinhoRepository.findById(1L)).thenReturn(Optional.of(carrinho));

        Carro carro = new Carro();
        carro.setId(1L);
        when(carroRepository.findById(1L)).thenReturn(Optional.of(carro));

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        // Executar o método a ser testado
        carrinhoService.adicionarVeiculo(1L, 1L, 1L, LocalDate.now(), LocalDate.now().plusDays(5));

        // Verificar interações
        verify(aluguelRepository, times(1)).save(any(Aluguel.class));
        verify(carrinhoRepository, times(1)).save(any(Carrinho.class));
    }

    @Test
    public void testConfirmarReserva() {
        // Configurar mocks
        Carrinho carrinho = new Carrinho();
        carrinho.setId(1L);
        Aluguel aluguel = new Aluguel();
        aluguel.setCliente(new Cliente());
        aluguel.setCarro(new Carro());
        carrinho.setAlugueis(List.of(aluguel));
        when(carrinhoRepository.findById(1L)).thenReturn(Optional.of(carrinho));

        // Executar o método a ser testado
        carrinhoService.confirmarReserva(1L);

        // Verificar interações
        verify(aluguelRepository, times(1)).save(any(Aluguel.class));
        verify(emailService, times(1)).enviarEmailConfirmacao(anyString(), anyString(), anyString());
    }
}*/
