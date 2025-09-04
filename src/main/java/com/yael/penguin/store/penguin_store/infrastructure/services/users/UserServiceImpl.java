package com.yael.penguin.store.penguin_store.infrastructure.services.users;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yael.penguin.store.penguin_store.domain.entities.users.UserEntity;
import com.yael.penguin.store.penguin_store.infrastructure.repositories.IUserRepository;
import com.yael.penguin.store.penguin_store.infrastructure.services.shared.SharedServiceImpl;





@Service
public class UserServiceImpl extends SharedServiceImpl<UserEntity, IUserRepository> implements IUserService {

    @Override
    public Optional<Boolean> findUserByEmail(String email) {
        return repository.findByEmail(email);
    }

}
