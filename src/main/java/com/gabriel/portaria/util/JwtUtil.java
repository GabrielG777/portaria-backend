package com.gabriel.portaria.util;

import com.gabriel.portaria.model.Funcionario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    // Chave secreta (em app real, use config externa e segura)
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String gerarToken(Funcionario funcionario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", funcionario.getId());
        claims.put("nome", funcionario.getNome());
        claims.put("cargo", funcionario.getCargo());
        claims.put("cnh", funcionario.getCnh());

        long agora = System.currentTimeMillis();
        long expiracao = 1000 * 60 * 60 * 24; // 24h

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(funcionario.getNome())
                .setIssuedAt(new Date(agora))
                .setExpiration(new Date(agora + expiracao))
                .signWith(key)
                .compact();
    }
}
