package sis.module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sis.module.model.Pessoas;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoas, Long> {
}
