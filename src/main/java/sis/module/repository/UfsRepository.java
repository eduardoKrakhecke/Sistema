package sis.module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sis.module.model.Ufs;

@Repository
public interface UfsRepository extends JpaRepository<Ufs, Long> {

}
