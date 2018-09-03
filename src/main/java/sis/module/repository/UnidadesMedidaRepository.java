package sis.module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sis.module.model.UnidadesMedida;

@Repository
public interface UnidadesMedidaRepository extends JpaRepository<UnidadesMedida, Long> {
}
