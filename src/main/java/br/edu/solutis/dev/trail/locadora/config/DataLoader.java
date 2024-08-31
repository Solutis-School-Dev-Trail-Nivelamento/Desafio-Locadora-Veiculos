package br.edu.solutis.dev.trail.locadora.config;

import br.edu.solutis.dev.trail.locadora.model.entity.*;
import br.edu.solutis.dev.trail.locadora.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class DataLoader {

    /*@Bean
    CommandLineRunner initDatabase(ClienteRepository clienteRepository, CarroRepository carroRepository, AluguelRepository aluguelRepository) {
        return args -> {
            Cliente cliente = new Cliente("Jose Silva", "12345678902", "jose.silva@example.com", LocalDate.of(1990, 1, 1), Sexo.MASCULINO, "123456783");
            clienteRepository.save(cliente);

            ModeloCarro modelo = new ModeloCarro(); // Supondo que você tenha uma entidade ModeloCarro
            modelo.setDescricao("Civic");

            Carro carro = new Carro("ABC-1234", "1HGCM82633A123456", "Preto", new BigDecimal("150.00"), modelo);
            carroRepository.save(carro);

            Aluguel aluguel = new Aluguel(cliente, carro, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 10));
            aluguelRepository.save(aluguel);
        };
    }*/
}
