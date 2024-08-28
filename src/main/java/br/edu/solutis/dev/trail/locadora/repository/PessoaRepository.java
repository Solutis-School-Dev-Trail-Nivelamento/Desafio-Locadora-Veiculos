package br.edu.solutis.dev.trail.locadora.repository;

import br.edu.solutis.dev.trail.locadora.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}