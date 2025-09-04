package com.yael.penguin.store.penguin_store.domain.entities.users;

import java.util.ArrayList;
import java.util.List;

import com.yael.penguin.store.penguin_store.domain.entities.EntityBase;
import com.yael.penguin.store.penguin_store.domain.entities.auth.RoleEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;




@Entity
@Table(name = "users")
public class UserEntity extends EntityBase {

    @Column(unique = true, length = 250, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<RoleEntity> roles = new ArrayList<>();


    // is required
    public UserEntity(){}


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<RoleEntity> getRoles() {
        return roles;
    }
    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

}

