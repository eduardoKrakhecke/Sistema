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
import sis.module.service.EstoquesService;

@RestController
@RequestMapping(value = "/user")
public class EstoquesController {

    @Autowired
    EstoquesService estoquesService;

    @RequestMapping(method = RequestMethod.GET, value = "/estoquePaginado", params = {"page", "size", "filtro"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pagina> buscarEstoquePaginado(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("filtro") String filtro) {
        Pagina pessoasBuscadas = estoquesService.estoquePaginado(page, size, filtro);
        return new ResponseEntity<>(pessoasBuscadas, HttpStatus.OK);
    }

}
