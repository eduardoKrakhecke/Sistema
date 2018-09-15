package sis.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sis.module.model.Paises;
import sis.module.service.PaisesService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class PaisesController {

    @Autowired
    PaisesService paisesService;

    @RequestMapping(method = RequestMethod.GET, value = "/paises", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Paises> buscarProdutosPaginados() {
        return  paisesService.buscaPaises();
    }
}
