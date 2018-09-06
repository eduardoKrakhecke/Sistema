package sis.module.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sis.module.model.Produtos;
import sis.module.repository.ProdutosRepository;

@Service
public class ProdutosService {

    @Autowired
    ProdutosRepository produtosRepository;

    public Page<Produtos> produtosPaginados(int page, int size) {
        return produtosRepository.findAll(new PageRequest(page, size));
    }
}
