package sis.module.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sis.module.repository.UnidadesMedidaRepository;

@Service
public class UnidadesMedidaService {

    @Autowired
    UnidadesMedidaRepository unidadesMedidaRepository;
}
