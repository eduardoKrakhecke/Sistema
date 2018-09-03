package sis.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sis.module.service.ProdutosService;

@RestController
@RequestMapping(value = "/user")
public class ProdutosController {

    @Autowired
    ProdutosService produtosService;
}
