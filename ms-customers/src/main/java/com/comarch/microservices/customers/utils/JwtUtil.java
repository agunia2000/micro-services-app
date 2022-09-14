package com.comarch.microservices.customers.utils;


import java.util.Date;

import com.comarch.microservices.customers.exception.JwtTokenMalformedException;
import com.comarch.microservices.customers.exception.JwtTokenMissingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final long tokenValidity = 7 * 24 * 60 * 60; // in seconds, here: one week = 7 days

    public String generateToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + tokenValidity * 1000;
        Date exp = new Date(expMillis);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

}