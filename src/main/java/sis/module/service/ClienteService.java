package sis.module.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sis.module.model.Cliente;
import sis.module.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;


    public Cliente cadastrar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void excluir(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    public Cliente buscaPorId(long id) {
        return clienteRepository.findOne(id);
    }

    public List<Cliente> busca() {
        return clienteRepository.findAll();
    }
}
