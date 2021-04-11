package com.br.brasilprev.customers.utils;

import com.br.brasilprev.customers.core.dto.auth.Userdata;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil implements Serializable {

        private static final long serialVersionUID = 1L;

        @Value("${springbootwebflux.jjwt.secret}")
        private String secret;

        @Value("${springbootwebflux.jjwt.expiration}")
        private String expirationTime;
        public Claims getAllClaimsFromToken(String token) {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                    .parseClaimsJws(token).getBody();
        }

        public String getUsernameFromToken(String token) {
            return getAllClaimsFromToken(token).getSubject();
        }

        public Date getExpirationDateFromToken(String token) {
            return getAllClaimsFromToken(token).getExpiration();
        }

        private Boolean isTokenExpired(String token) {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        }

        public String generateToken(Userdata user) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", user.getRoles());
            return doGenerateToken(claims, user.getEmail());
        }

        private String doGenerateToken(Map<String, Object> claims, String username) {
            Long expirationTimeLong = Long.parseLong(expirationTime); //in second
            byte[] apiKeySecretBytes=DatatypeConverter.parseBase64Binary(secret);

            final Date createdDate = new Date();
            final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(username)
                    .setIssuedAt(createdDate)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS256, apiKeySecretBytes)
                    .compact();
        }

        public Boolean validateToken(String token) {
            return !isTokenExpired(token);
        }
}
