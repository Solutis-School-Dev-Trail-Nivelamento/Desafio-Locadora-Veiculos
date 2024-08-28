package br.edu.solutis.dev.trail.locadora.repository;

import br.edu.solutis.dev.trail.locadora.model.entity.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    boolean existsByCnh(String cnh);
}
