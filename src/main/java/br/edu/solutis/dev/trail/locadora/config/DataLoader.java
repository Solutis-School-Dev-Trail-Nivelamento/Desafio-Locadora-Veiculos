package br.edu.solutis.dev.trail.locadora.config;

import br.edu.solutis.dev.trail.locadora.model.entity.*;
import br.edu.solutis.dev.trail.locadora.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataLoader {

  @Bean
    CommandLineRunner initDatabase(ClienteRepository clienteRepository, CarroRepository carroRepository, ModeloCarroRepository modeloRepository, FabricanteRepository fabricanteRepository, AcessorioRepository acessorioRepository) {
        return args -> {
           // Criando clientes
          /*  Cliente cliente1 = new Cliente("Marina Silva", "12345678903", "marina.silva@example.com", LocalDate.of(1990, 1, 1), Sexo.FEMININO, "123456788");
            Cliente cliente2 = new Cliente("Jose Silva", "12345678902", "jose.silva@example.com", LocalDate.of(1990, 1, 1), Sexo.MASCULINO, "123456782");
            Cliente cliente3 = new Cliente("Maria Silva", "12345678901", "maria.silva@example.com", LocalDate.of(1990, 1, 1), Sexo.FEMININO, "123456783");
            Cliente cliente4 = new Cliente("João Silva", "12345678900", "joao.silva@example.com", LocalDate.of(1990, 1, 1), Sexo.MASCULINO, "123456786");
            clienteRepository.save(cliente1);
            clienteRepository.save(cliente2);
            clienteRepository.save(cliente3);
            clienteRepository.save(cliente4);
*/
            /*
            // Criando fabricante
            Fabricante fabricante = new Fabricante();
            fabricante.setNome("FORD");
            fabricanteRepository.save(fabricante);

            // Criando modelo
            ModeloCarro modelo = new ModeloCarro();
            modelo.setId(1L);
            modelo.setDescricao("City");
            modelo.setCategoria(Categoria.SEDAN_COMPACTO);
            modelo.setFabricante(fabricante);
            modeloRepository.save(modelo);

            // Criando acessório
            Acessorio acessorio = new Acessorio();
            acessorio.setId(1L);
            acessorio.setDescricao("Som");
            acessorioRepository.save(acessorio);

            // Criando carro
            Carro carro = new Carro();
            carro.setPlaca("CLB-5653");
            carro.setChassi("345DSG54GDS");
            carro.setCor("BRANCO");
            carro.setValorDiaria(new BigDecimal("150.00"));
            carro.setModelo(modelo);
            carro.setAcessorios(List.of(acessorio));
            carroRepository.save(carro);
*/


        };
    }
}
