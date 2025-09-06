package com.yael.penguin.store.penguin_store.application.interfaces;

import com.yael.penguin.store.penguin_store.application.dtos.auth.CustomUserAuthDto;




public interface IJwtService {
    public String createJwt(CustomUserAuthDto auth);
    public String createJwt(CustomUserAuthDto auth, Long durationMs);
    public Boolean hasExpiredToken(String token);
    public CustomUserAuthDto decodeToken(String token);
}
