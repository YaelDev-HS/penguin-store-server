package com.yael.penguin.store.penguin_store.application.useCases.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yael.penguin.store.penguin_store.application.dtos.auth.LoginDto;
import com.yael.penguin.store.penguin_store.application.dtos.auth.RegisterUserDto;
import com.yael.penguin.store.penguin_store.domain.customErrors.CustomError;
import com.yael.penguin.store.penguin_store.domain.entities.users.UserEntity;
import com.yael.penguin.store.penguin_store.infrastructure.services.users.IUserService;





@Component
public class AuthUseCases {

    @Autowired
    private IUserService userService;



    public UserEntity registerUser(RegisterUserDto dto){
        Optional<Boolean> accountExists = userService.findUserByEmail(dto.getEmail());
        if( accountExists.isPresent() ) throw CustomError.badRequest("Account already exists");

        UserEntity user = new UserEntity();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return userService.save(user);
    }

    public UserEntity loginUser(LoginDto dto){
        UserEntity user = userService.findByEmail(dto.getEmail(), UserEntity.class)
            .orElseThrow(() -> CustomError.notFound("email or password is not valid"));

        // TODO: verificar la contrasena encriptada
        Boolean matchPassword = true;
        if( !matchPassword ) throw CustomError.badRequest("email or password is not valid");
        // TODO: generar el token de acceso

        return user;
    }


}
