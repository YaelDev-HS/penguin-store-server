package com.yael.penguin.store.penguin_store.infrastructure.services.users;

import java.util.Optional;

import com.yael.penguin.store.penguin_store.domain.entities.users.UserEntity;
import com.yael.penguin.store.penguin_store.infrastructure.services.shared.ISharedService;




public interface IUserService extends ISharedService<UserEntity> {

    public Optional<Boolean> findUserByEmail(String email);
    public <P> Optional<P> findByEmail(String email, Class<P> projection);

}
