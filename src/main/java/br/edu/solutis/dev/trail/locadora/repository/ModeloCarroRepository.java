package br.edu.solutis.dev.trail.locadora.repository;


import br.edu.solutis.dev.trail.locadora.model.entity.ModeloCarro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloCarroRepository extends JpaRepository<ModeloCarro, Long> {
   boolean existsByDescricao (String descricao);
}

