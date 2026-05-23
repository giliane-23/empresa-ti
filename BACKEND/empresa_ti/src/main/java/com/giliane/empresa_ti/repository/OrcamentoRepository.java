package com.giliane.empresa_ti.repository;
import java.util.Optional;
import com.giliane.empresa_ti.model.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
    Optional<Orcamento> findByEmail(String email);
}