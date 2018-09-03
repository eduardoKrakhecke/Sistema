package sis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sis.model.Usuarios;
import sis.security.Crypto;
import sis.security.JwtTokenUtil;
import sis.service.UsuariosService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    UsuariosService usuariosService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    static final String HEADER_STRING = "Authorization";


    @RequestMapping(value = "/autenticar", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public ResponseEntity<?> autenticar(@RequestBody Usuarios usuarios, HttpServletResponse response, HttpServletRequest request) throws ServletException, UnsupportedEncodingException, NoSuchAlgorithmException {

        Crypto passwordEncrypt = new Crypto();

        if (usuarios.getLogin() == null || usuarios.getSenha() == null) {
            throw new ServletException("Nome ou senha obrigatório");
        }

        Usuarios usuariosAutenticado = usuariosService.buscarPorLogin(usuarios.getLogin());

        if (usuariosAutenticado == null) {
            return new ResponseEntity<String>("naoEncontrado", HttpStatus.UNAUTHORIZED);
        }

        String senhaCrypt = passwordEncrypt.getSHA256Hash(usuarios.getSenha());
        if (!usuariosAutenticado.getSenha().equalsIgnoreCase(senhaCrypt)) {
            return new ResponseEntity<String>("senhaInvalida", HttpStatus.UNAUTHORIZED);
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("admin", usuariosAutenticado.getFlagAdministrador() == 1);
        claims.put("username", usuariosAutenticado.getLogin());
        claims.put("userkey", usuariosAutenticado.getIdUsuario());

        HttpSession session = request.getSession();
        Long i = usuariosAutenticado.getIdUsuario();
        session.setAttribute("i", i);

        final String token = jwtTokenUtil.generateToken(usuariosAutenticado.getLogin(), claims);

        Cookie cookie = new Cookie("userToken", token);
        cookie.setPath("/");
        cookie.setMaxAge(30*24*60*60*1000);
        response.addCookie(cookie);

        return new ResponseEntity<String>("", HttpStatus.OK);
    }

}
