package com.yael.penguin.store.penguin_store.application.dtos.auth;

import java.util.List;





public class CustomUserAuthDto {

    private String email;
    private Long accountId;
    private List<String> roles;


    public CustomUserAuthDto(){}

    public CustomUserAuthDto(String email, Long accountId, List<String> roles){
        this.email = email;
        this.accountId = accountId;
        this.roles = roles;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getAccountId() {
        return accountId;
    }
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
