package sis.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sis.module.model.Produtos;
import sis.module.service.ProdutosService;

@RestController
@RequestMapping(value = "/user")
public class ProdutosController {

    @Autowired
    ProdutosService produtosService;

    @RequestMapping(method = RequestMethod.GET, value = "/produtosPaginados", params = {"page", "size"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Produtos> buscarProdutosPaginados(@RequestParam("page") int page, @RequestParam("size") int size) {
         Page resultadoPaginado = produtosService.produtosPaginados(page, size);
        if (page > resultadoPaginado.getTotalPages()) {
            System.out.print("sem resultado da paginação");
        }
        return resultadoPaginado;
    }
}
