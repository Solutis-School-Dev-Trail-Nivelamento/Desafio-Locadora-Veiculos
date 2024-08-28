package br.edu.solutis.dev.trail.locadora.repository;


import br.edu.solutis.dev.trail.locadora.model.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloCarroRepository extends JpaRepository<Carro, Long> {
   // boolean existsBy // para não ser duplicado atualizar mais tarde
}

