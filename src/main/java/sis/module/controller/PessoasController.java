package sis.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sis.model.Pagina;
import sis.module.model.Pessoas;
import sis.module.model.PessoasDocumentos;
import sis.module.service.PessoasService;

@RestController
@RequestMapping(value = "/user")
public class PessoasController {

    @Autowired
    PessoasService pessoasService;

    @RequestMapping(method = RequestMethod.GET, value = "/pessoasPaginadas", params = {"page", "size", "filtro"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pagina> buscarPessoasPaginadas(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("filtro") String filtro) {
        Pagina pessoasBuscadas = pessoasService.pessoasPaginadas(page, size, filtro);
        return new ResponseEntity<>(pessoasBuscadas, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/pessoas", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus cadastrar(@RequestBody Pessoas pessoa){
        pessoasService.cadastrar(pessoa);
        return HttpStatus.CREATED;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/pessoa/{id}")
    public ResponseEntity<Pessoas> excluirPessoa(@PathVariable int id) {
        Pessoas pes = pessoasService.buscaPorId(id);
        pessoasService.excluir(pes);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
