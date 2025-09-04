package com.yael.penguin.store.penguin_store.application.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;




public class EmailDto {

    @NotNull
    @NotBlank
    @Email
    private String email;


    public String getEmail() {
        return email.trim().toLowerCase();
    }
    public void setEmail(String email) {
        this.email = email;
    }


}
