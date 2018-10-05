package sis.module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sis.module.model.Produtos;

import java.util.List;

@Repository
public interface ProdutosRepository  extends JpaRepository<Produtos, Long> {
    @Query( "select p from Produtos p where UPPER(p.descricao) like UPPER(?1) or UPPER(p.descricao) like UPPER(?1)")
    List search(String parametro);
}
