package sis.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sis.module.model.Ufs;
import sis.module.service.UfsService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UfsController {

    @Autowired
    UfsService ufsService;

    @RequestMapping(method = RequestMethod.GET, value = "/ufs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ufs> buscarProdutosPaginados() {
        return  ufsService.buscaUfs();
    }
}
