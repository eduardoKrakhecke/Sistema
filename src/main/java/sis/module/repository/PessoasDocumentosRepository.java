package sis.module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sis.module.model.PessoasDocumentos;

@Repository
public interface PessoasDocumentosRepository extends JpaRepository<PessoasDocumentos, Long> {


}
