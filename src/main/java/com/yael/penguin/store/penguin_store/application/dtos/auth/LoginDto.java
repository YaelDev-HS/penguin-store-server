package com.yael.penguin.store.penguin_store.application.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public class LoginDto extends EmailDto {

    // TODO: validacion personalizada
    @NotNull
    @NotBlank
    private String password;


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
