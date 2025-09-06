package com.yael.penguin.store.penguin_store.application.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yael.penguin.store.penguin_store.application.dtos.auth.CustomUserAuthDto;
import com.yael.penguin.store.penguin_store.application.interfaces.IJwtService;
import com.yael.penguin.store.penguin_store.infrastructure.services.users.IUserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




@Service
public class AuthFilterService extends OncePerRequestFilter {

    @Autowired
    private IJwtService jwtService;
    @Autowired
    private IUserService userService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerAuthorization = request.getHeader("Authorization");

        if( headerAuthorization == null ){
            filterChain.doFilter(request, response);
            return;
        }

        Boolean bearer = headerAuthorization.startsWith("Bearer ");
        if( !bearer ){
            filterChain.doFilter(request, response);
            return;
        }

        String token = headerAuthorization.substring(7);
        if( jwtService.hasExpiredToken(token) ){
            // TODO: enviar un error 401
            return;
        }

        CustomUserAuthDto auth = jwtService.decodeToken(token);
        if( auth == null ){
            //TODO: enviar un error 401
            return;
        }

        Optional<Boolean> isActive = userService.findUserByEmail(auth.getEmail());
        if( isActive.isEmpty() || !isActive.get() ){
            // TODO: 401
            return;
        }

        UsernamePasswordAuthenticationToken userAuthenticated = new UsernamePasswordAuthenticationToken(
            auth,
            null,
            auth.getRoles()
                .stream()
                .map(SimpleGrantedAuthority::new).toList()
        );

        SecurityContextHolder.getContext().setAuthentication(userAuthenticated);
    }

}
