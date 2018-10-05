package sis.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sis.module.model.UnidadesMedida;
import sis.module.service.UnidadesMedidaService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UnidadesMedidaController {

    @Autowired
    UnidadesMedidaService unidadesMedidaService;

    @RequestMapping(method = RequestMethod.GET, value = "/unidadesMedidas", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UnidadesMedida> buscarProdutosPaginados() {
        return  unidadesMedidaService.buscaUnidades();
    }
}
