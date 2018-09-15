package sis.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sis.module.model.Municipios;
import sis.module.service.MunicipiosService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class MunicipiosController {

    @Autowired
    MunicipiosService municipiosService;

    @RequestMapping(method = RequestMethod.GET, value = "/municipios", params = {"idUf"})
    public List<Municipios> buscarMunicipiosPelaUf(@RequestParam("idUf") long idUf) {
        return municipiosService.buscarPorUf(idUf);
    }


}
