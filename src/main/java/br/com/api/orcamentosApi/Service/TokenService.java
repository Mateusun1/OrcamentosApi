package br.com.api.orcamentosApi.Service;

import br.com.api.orcamentosApi.Modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.util.Date;

@Service
public class TokenService {

    @Value("${orcamentosApi.jwt.expiration}")
    private String expiracao;
    @Value("${orcamentosApi.jwt.secret}")
    private String secret;
    public String gerarToken(Authentication auth) {

        Usuario principal = (Usuario) auth.getPrincipal();
        Date hoje = new Date();
        Date expiration = new Date(hoje.getTime() + Long.parseLong(expiracao));
        return Jwts.builder()
                .setIssuer("API de Orçamentos")
                .setSubject(principal.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean isValido(String token) {
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.parseLong(body.getSubject());
    }
}
