package com.yael.penguin.store.penguin_store.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yael.penguin.store.penguin_store.domain.entities.users.UserEntity;





public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    @Query("""
        SELECT u.enabled FROM UserEntity u
        WHERE u.email = ?1
    """)
    public Optional<Boolean> findByEmail(String email);

}
