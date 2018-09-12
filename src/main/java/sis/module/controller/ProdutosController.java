package sis.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sis.model.Pagina;
import sis.module.model.Produtos;
import sis.module.service.ProdutosService;

@RestController
@RequestMapping(value = "/user")
public class ProdutosController {

    @Autowired
    ProdutosService produtosService;

    @RequestMapping(method = RequestMethod.GET, value = "/produtosPaginados", params = {"page", "size", "filtro"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Pagina> buscarProdutosPaginados(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("filtro") String filtro) {
         Pagina produtosBuscados = produtosService.produtosPaginados(page, size, filtro);
        return new ResponseEntity<>(produtosBuscados, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/produtos", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus cadastrar(@RequestBody Produtos produto){
        produtosService.cadastrar(produto);
        return HttpStatus.CREATED;
    }




}
