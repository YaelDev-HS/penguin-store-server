package com.yael.penguin.store.penguin_store.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yael.penguin.store.penguin_store.application.dtos.auth.RegisterUserDto;
import com.yael.penguin.store.penguin_store.application.useCases.auth.AuthUseCases;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/api/auth") //localhost:8080/api/auth
public class AuthController {

    @Autowired
    private AuthUseCases authUseCases;



    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDto dto ){
        var res = authUseCases.registerUser(dto);
        return ResponseEntity.ok(res);
    }


}

