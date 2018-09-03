package sis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sis.model.Usuarios;
import sis.repository.UsuariosRepository;

@Service
public class UsuariosService {

    @Autowired
    UsuariosRepository usuariosRepository;

    public Usuarios buscarPorLogin(String usuarios) {
        return usuariosRepository.buscarPorLogin(usuarios);
    }

    public Usuarios cadastrar(Usuarios usuario){
        return usuariosRepository.save(usuario);
    }

    public Usuarios buscarUsuarioPorId(Long idUsuLogado) {
        return usuariosRepository.findOne(idUsuLogado);
    }
}
