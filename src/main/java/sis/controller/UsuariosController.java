package sis.controller;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sis.model.Usuarios;
import sis.security.JwtTokenUtil;
import sis.service.UsuariosService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/user")
public class UsuariosController {

    @Autowired
    UsuariosService usuariosService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    static final String HEADER_STRING = "Authorization";

    @RequestMapping(method = RequestMethod.GET, value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuarios> buscarUsuario(HttpServletRequest request) throws ServletException, ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
        HttpSession session = request.getSession(true);

        Usuarios uBuscado = usuariosService.buscarUsuarioPorId((Long)session.getAttribute("i"));
        uBuscado.setSenha("***");
        return new ResponseEntity<>(uBuscado, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/usuario", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuarios> alterarUsuario(@RequestBody Usuarios usuario) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (usuario.getSenha().equals("***")) {
            Usuarios uBuscado = usuariosService.buscarUsuarioPorId(usuario.getIdUsuario());
            usuario.setSenha(uBuscado.getSenha());
        } else {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(usuario.getSenha().getBytes(StandardCharsets.UTF_8));
            String senha = bytesToHex(encodedhash);
            usuario.setSenha(senha);
        }

        Usuarios usuarioCadastrado = usuariosService.cadastrar(usuario);
        return new ResponseEntity<Usuarios>(usuarioCadastrado, HttpStatus.CREATED);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
