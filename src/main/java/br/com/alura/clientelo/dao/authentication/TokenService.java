package br.com.alura.clientelo.dao.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private final String expiration;
    private final String secret;
    private final String ISSUER_NAME = "CLIENTEELO";

    public TokenService(@Value("${jwt.expiration}") String expiration,@Value("${jwt.secret}") String secret) {
        this.expiration = expiration;
        this.secret = secret;
    }

    public String gerar(String username) {
        Date data = new Date();
        Date expirar = new Date(data.getTime() + Long.parseLong(expiration));

        return Jwts.builder().setIssuer(ISSUER_NAME)
                .setIssuedAt(data)
                .setExpiration(expirar)
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

}
