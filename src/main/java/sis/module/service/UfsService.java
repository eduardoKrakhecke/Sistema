package sis.module.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sis.module.model.Ufs;
import sis.module.repository.UfsRepository;

import java.util.List;

@Service
public class UfsService {

    @Autowired
    UfsRepository ufsRepository;

    public List<Ufs> buscaUfs() {
        return ufsRepository.findAll(sortByNomeAsc());
    }

    private Sort sortByNomeAsc() {
        return new Sort(Sort.Direction.ASC, "nome");
    }
}
