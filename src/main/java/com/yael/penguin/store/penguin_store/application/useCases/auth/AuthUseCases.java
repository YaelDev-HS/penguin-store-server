package com.yael.penguin.store.penguin_store.application.useCases.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


}
