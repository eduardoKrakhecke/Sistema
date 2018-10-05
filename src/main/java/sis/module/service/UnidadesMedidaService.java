package sis.module.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sis.module.model.UnidadesMedida;
import sis.module.repository.UnidadesMedidaRepository;

import java.util.List;

@Service
public class UnidadesMedidaService {

    @Autowired
    UnidadesMedidaRepository unidadesMedidaRepository;

    public List<UnidadesMedida> buscaUnidades() {
        return unidadesMedidaRepository.findAll(sortByNomeAsc());
    }

    private Sort sortByNomeAsc() {
        return new Sort(Sort.Direction.ASC, "descricao");
    }
}
