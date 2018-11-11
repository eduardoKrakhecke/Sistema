package sis.module.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sis.module.model.Risco;
import sis.module.repository.RiscoRepository;

import java.util.List;

@Service
public class RiscoService {

    @Autowired
    RiscoRepository riscoRepository;

    public Risco cadastrar(Risco risco) {
        return riscoRepository.save(risco);
    }

    public void excluir(Risco risco) {
        riscoRepository.delete(risco);
    }

    public Risco buscaPorId(long id) {
        return riscoRepository.findOne(id);
    }

    public List<Risco> buscaRisco() {
        return riscoRepository.findAll();
    }

}
