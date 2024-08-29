package br.edu.solutis.dev.trail.locadora.repository;


import br.edu.solutis.dev.trail.locadora.model.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<< HEAD
public interface ModeloCarroRepository extends JpaRepository<Carro, Long> {
   // boolean existsBy // para não ser duplicado atualizar mais tarde
=======
public interface ModeloCarroRepository extends JpaRepository<ModeloCarro, Long> {
   boolean existsByDescricao (String descricao);
>>>>>>> e5d40ca84ecfe6020fc4f0d14a5ea604b2fe03c8
}

