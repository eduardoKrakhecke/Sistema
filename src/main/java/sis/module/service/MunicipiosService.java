package sis.module.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sis.module.model.Municipios;
import sis.module.repository.MunicipiosRepository;

import java.util.List;

@Service
public class MunicipiosService {

    @Autowired
    MunicipiosRepository municipiosRepository;

    public List<Municipios> buscarPorUf(long idUf) {
        return municipiosRepository.buscarPorUf(idUf);
    }
}
