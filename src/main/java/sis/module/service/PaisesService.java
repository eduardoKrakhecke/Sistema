package sis.module.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sis.module.model.Paises;
import sis.module.repository.PaisesRepository;

import java.util.List;

@Service
public class PaisesService {

    @Autowired
    PaisesRepository paisesRepository;

    public List<Paises> buscaPaises() {
        return paisesRepository.findAll(sortByNomeAsc());
    }

    private Sort sortByNomeAsc() {
        return new Sort(Sort.Direction.ASC, "nome");
    }
}
