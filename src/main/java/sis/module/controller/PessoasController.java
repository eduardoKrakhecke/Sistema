package sis.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sis.model.Pagina;
import sis.module.model.Pessoas;
import sis.module.model.PessoasDocumentos;
import sis.module.service.PessoasDocumentosService;
import sis.module.service.PessoasService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class PessoasController {

    @Autowired
    PessoasService pessoasService;

    @Autowired
    PessoasDocumentosService pessoasDocumentosService;

    @RequestMapping(method = RequestMethod.GET, value = "/pessoasPaginadas", params = {"page", "size", "filtro"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pagina> buscarPessoasPaginadas(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("filtro") String filtro) {
        Pagina pessoasBuscadas = pessoasService.pessoasPaginadas(page, size, filtro);
        return new ResponseEntity<>(pessoasBuscadas, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/pessoas", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus cadastrar(@RequestBody Pessoas pessoa){
        List<PessoasDocumentos> pesDocList = new ArrayList<>();
        pessoasService.cadastrar(pessoa);
        for (int i = 0; i <pessoa.getPessoaDocumentos().size(); i++){
            PessoasDocumentos pesDoc = new PessoasDocumentos();
            pesDoc.setDocumento(pessoa.getPessoaDocumentos().get(i).getDocumento());
            pesDoc.setTipoDocumento(pessoa.getPessoaDocumentos().get(i).getTipoDocumento());
            pesDoc.setPessoa(pessoa);
            pesDocList.add(pesDoc);
        }
        pessoasDocumentosService.cadastrar(pesDocList);
        return HttpStatus.CREATED;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/pessoa/{id}")
    public ResponseEntity<Pessoas> excluirPessoa(@PathVariable int id) {
        Pessoas pes = pessoasService.buscaPorId(id);
        pessoasService.excluir(pes);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
