package org.example.safety;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;

    public JWTTokenProvider(@Value("${jwt.secret}") String key) {
        this.secretKey = key;
        System.out.println("DEBUG: Construtor JWTTokenProvider - secretKey (inicial): " + (secretKey != null ? secretKey : "NULL"));
    }

    public String generateToken(String email){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Constants.VALIDITY);

        System.out.println("Chave: " + secretKey);

        return Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUserFromToken(String token){
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        }catch (Exception e){
            return false;
        }
    }

}
