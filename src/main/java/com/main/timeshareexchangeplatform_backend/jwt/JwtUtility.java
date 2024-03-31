package com.main.timeshareexchangeplatform_backend.jwt;

import java.security.Key;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.main.timeshareexchangeplatform_backend.payload.LoginResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtility {
    public static final String SECRETKEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60;// 60m

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(LoginResponse loginResponse) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", loginResponse.getUser_name());
        claims.put("role", loginResponse.getRole());
        claims.put("fullname", loginResponse.getFull_name());
        claims.put("userid", loginResponse.getUserid());
        claims.put("email", loginResponse.getEmail());
        LocalDate dob = loginResponse.getDob();
        if (dob != null) {
            claims.put("dob", dob.toString());
        }
        claims.put("gender", loginResponse.getGender());
        claims.put("phone", loginResponse.getPhone());
        return createToken(claims, loginResponse.getUser_name());
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRETKEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
