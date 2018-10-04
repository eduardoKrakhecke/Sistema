package sis.module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sis.module.model.Estoques;

@Repository
public interface EstoquesRepository extends JpaRepository<Estoques, Long> {
}
