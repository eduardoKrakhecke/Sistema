package sis.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sis.module.model.Cliente;

import sis.module.service.ClienteService;

import java.util.List;


@RestController
@RequestMapping(value = "/user")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @RequestMapping(method = RequestMethod.GET, value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cliente> buscarClientes() {
        return clienteService.busca();
    }


   @RequestMapping(method = RequestMethod.POST, value = "/cliente", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus cadastrar(@RequestBody Cliente cliente){
       clienteService.cadastrar(cliente);
        return HttpStatus.CREATED;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cliente/{id}")
    public ResponseEntity<Cliente> excluir(@PathVariable int id) {
        Cliente cliente = clienteService.buscaPorId(id);
        clienteService.excluir(cliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
