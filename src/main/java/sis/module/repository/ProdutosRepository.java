package sis.module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sis.module.model.Produtos;

@Repository
public interface ProdutosRepository  extends JpaRepository<Produtos, Long> {
}
