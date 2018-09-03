package sis.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw new ServletException("Token inexiste ou inválido");
        }
        String token = header.substring(7); // extraindo somente a string do token sem o bearer

        // verificar se o token é válido
        try {
            Jwts.parser().setSigningKey("5e5a0a2d9cce418352131877927b97601f25a4273570073616bc94e53cbedb841695bea6c30acaee9d3f077b63e9e03c1a78e28efccd8131ebdadd75fea7132a".getBytes("UTF-8")).parseClaimsJws(token).getBody();
        } catch (SignatureException e) {
            // throw new ServletException("Token inválido");
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Não Autorizado");
        }

        chain.doFilter(request, response);
    }
}
