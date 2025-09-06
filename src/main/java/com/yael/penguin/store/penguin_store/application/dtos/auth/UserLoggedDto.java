package com.yael.penguin.store.penguin_store.application.dtos.auth;

import java.util.List;





public class UserLoggedDto {

    private String email;
    private Long accountId;
    private List<String> roles;
    private String token;


    public UserLoggedDto(String email, Long accountId, List<String> roles, String token){
        this.email = email;
        this.accountId = accountId;
        this.roles = roles;
        this.token = token;
    }


    public String getEmail() {
        return email;
    }
    public Long getAccountId() {
        return accountId;
    }
    public List<String> getRoles() {
        return roles;
    }
    public String getToken() {
        return token;
    }

}
