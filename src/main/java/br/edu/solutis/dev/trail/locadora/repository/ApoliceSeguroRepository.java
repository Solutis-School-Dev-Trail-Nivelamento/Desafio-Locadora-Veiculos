package br.edu.solutis.dev.trail.locadora.repository;

import br.edu.solutis.dev.trail.locadora.model.entity.ApoliceSeguro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApoliceSeguroRepository extends JpaRepository<ApoliceSeguro, Long> {
    // Aqui você pode adicionar métodos de consulta personalizados, se necessário
}

