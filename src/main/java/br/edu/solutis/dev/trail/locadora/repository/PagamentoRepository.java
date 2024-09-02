package br.edu.solutis.dev.trail.locadora.repository;

import br.edu.solutis.dev.trail.locadora.model.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
