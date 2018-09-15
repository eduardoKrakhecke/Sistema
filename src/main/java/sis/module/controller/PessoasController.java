package sis.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sis.model.Pagina;
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
}
