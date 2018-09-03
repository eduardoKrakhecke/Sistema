package sis.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3376117656006649029L;

    static final String SECRET = "5e5a0a2d9cce418352131877927b97601f25a4273570073616bc94e53cbedb841695bea6c30acaee9d3f077b63e9e03c1a78e28efccd8131ebdadd75fea7132a";
    static final String AUDIENCE_WEB = "web";


    public String getUsernameFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String getAudienceFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
        return getClaimFromToken(token, Claims::getAudience);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public static Claims getAllClaimsFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
        return Jwts.parser()
                .setSigningKey(SECRET.getBytes("UTF-8"))
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(String login, Map<String, Object> claims) throws UnsupportedEncodingException {
        return doGenerateToken(claims, login, AUDIENCE_WEB);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, String audience) throws UnsupportedEncodingException {

        return Jwts.builder()
                .setId("24e3c04d-5354-41be-ad4b-f430f3037859")
                .setClaims(claims)
                .setSubject(subject)
                .setAudience(audience)
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes("UTF-8"))
                .compact();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime()- (30*24*60*60*1000));
    }}
