package sis.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sis.module.model.Risco;
import sis.module.service.RiscoService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class RiscoController {

    @Autowired
    RiscoService riscoService;

    @RequestMapping(method = RequestMethod.GET, value = "/risco", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Risco> buscarRiscos() {
        return  riscoService.buscaRisco();
    }
}
