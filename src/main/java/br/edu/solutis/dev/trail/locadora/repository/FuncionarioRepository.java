package br.edu.solutis.dev.trail.locadora.repository;

import br.edu.solutis.dev.trail.locadora.model.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    boolean existsByMatricula(String matricula);
}