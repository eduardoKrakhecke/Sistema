package sis.module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sis.module.model.Risco;

@Repository
public interface RiscoRepository extends JpaRepository<Risco, Long> {

}
