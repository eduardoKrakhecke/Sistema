package sis.module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sis.module.model.Paises;

@Repository
public interface PaisesRepository  extends JpaRepository<Paises, Long> {

}
