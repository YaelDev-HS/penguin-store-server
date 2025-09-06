package com.yael.penguin.store.penguin_store.application.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.yael.penguin.store.penguin_store.application.dtos.auth.CustomUserAuthDto;
import com.yael.penguin.store.penguin_store.application.interfaces.IJwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;




@Service
public class JwtServiceImpl implements IJwtService {

    private SecretKey key = Jwts.SIG.HS256.key().build();


    @Override
    public String createJwt(CustomUserAuthDto auth) {
        return createJwt(auth, TimeUnit.MINUTES.toMillis(30));
    }

    @Override
    public String createJwt(CustomUserAuthDto auth, Long durationMs) {
        try {
            Date expiredAt = new Date(System.currentTimeMillis() + durationMs);

            String token = Jwts.builder()
                .claim("roles", auth.getRoles())
                .claim("accountId", auth.getAccountId())
                .subject(auth.getEmail())
                .issuedAt(new Date())
                .expiration(expiredAt)
                .signWith(key)
                .compact();

            return token;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean hasExpiredToken(String token) {
        try {
            getClaimsByToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public CustomUserAuthDto decodeToken(String token) {
        try {
            Claims claims = getClaimsByToken(token);

            String email = claims.getSubject();
            Long accountId = claims.get("accountId", Long.class);
            Object rolesObj = claims.get("roles");
            List<String> roles = new ArrayList<>();

            if(rolesObj != null && rolesObj instanceof ArrayList){
                ((List<?>) rolesObj)
                    .forEach(r -> {
                        if( r instanceof String ){
                            roles.add((String) r);
                        }
                    });
            }

            return new CustomUserAuthDto(email, accountId, roles);
        } catch (Exception e) {
            return null;
        }
    }

    private Claims getClaimsByToken(String token){
        // Si el token expira da un error.
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

}
