package sis.module.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sis.module.repository.ProdutosRepository;

@Service
public class ProdutosService {

    @Autowired
    ProdutosRepository produtosRepository;
}
