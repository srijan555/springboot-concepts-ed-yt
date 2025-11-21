package com.ed.springboot.cricketteam.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {
    private final String SECRET="ChangeThisSecretKeyToSomethingElse";

    private final long EXPIRATION_MS=1000*60*15; // 15 minute

    public String generateToken(String username, List<String> roles){
        Claims claims= Jwts.claims().setSubject(username);
        claims.put("roles",roles);
        System.out.println("[JwtUtil] generateToken() called for username: " + username);
        System.out.println("[JwtUtil] Roles: " + roles);
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        System.out.println("[JwtUtil] Generated token: " + token);
        return token;
    }

    public boolean validateToken(String token){
        try {
            System.out.println("[JwtUtil] validateToken() called for token: " + token);
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public String extractUsername(String token){
        System.out.println("[JwtUtil] extractUsername() called for token: " + token);
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        System.out.println("[JwtUtil] Extracted claims: " + claims);
        return claims.getSubject();
    }

    public String generateRefreshToken(String username){
        System.out.println("[JwtUtil] generateRefreshToken() called for username: " + username);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7) )) // 7 days
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        System.out.println("[JwtUtil] Generated refresh token: " + token);
        return token;
    }

    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token){
        System.out.println("[JwtUtil] extractRoles() called for token: " + token);
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        System.out.println("[JwtUtil] Extracted claims: " + claims);
        Object rolesObj = claims.get("roles");
        if(rolesObj instanceof List){
            System.out.println("[JwtUtil] Extracted roles: " + rolesObj);
            return (List<String>) rolesObj;
        }
        return new ArrayList<>();
    }
}
