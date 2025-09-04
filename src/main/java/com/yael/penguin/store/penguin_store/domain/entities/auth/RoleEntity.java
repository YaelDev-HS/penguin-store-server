package com.yael.penguin.store.penguin_store.domain.entities.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    private String role;


    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

}
