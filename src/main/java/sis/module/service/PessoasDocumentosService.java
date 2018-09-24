package sis.module.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sis.module.model.PessoasDocumentos;
import sis.module.repository.PessoasDocumentosRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PessoasDocumentosService {
    @Autowired
    PessoasDocumentosRepository pessoasDocumentosRepository;

    @Transactional
    public List<PessoasDocumentos> cadastrar(List<PessoasDocumentos> pesDoc) {
        return pessoasDocumentosRepository.save(pesDoc);
    }

    public void excluir(PessoasDocumentos pesDoc) {
        pessoasDocumentosRepository.delete(pesDoc);
    }

    public PessoasDocumentos buscaPorId(long id) {
        return pessoasDocumentosRepository.findOne(id);
    }

}
