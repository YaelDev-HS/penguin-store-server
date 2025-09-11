package com.yael.penguin.store.penguin_store.application.useCases.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.yael.penguin.store.penguin_store.application.dtos.auth.CustomUserAuthDto;
import com.yael.penguin.store.penguin_store.application.dtos.auth.LoginDto;
import com.yael.penguin.store.penguin_store.application.dtos.auth.RegisterUserDto;
import com.yael.penguin.store.penguin_store.application.dtos.auth.UserLoggedDto;
import com.yael.penguin.store.penguin_store.application.interfaces.IJwtService;
import com.yael.penguin.store.penguin_store.domain.customErrors.CustomError;
import com.yael.penguin.store.penguin_store.domain.entities.auth.RoleEntity;
import com.yael.penguin.store.penguin_store.domain.entities.users.UserEntity;
import com.yael.penguin.store.penguin_store.infrastructure.services.users.IUserService;





@Component
public class AuthUseCases {

    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private IJwtService jwtService;



    public UserLoggedDto registerUser(RegisterUserDto dto){
        Optional<Boolean> accountExists = userService.findUserByEmail(dto.getEmail());
        if( accountExists.isPresent() ) throw CustomError.badRequest("Account already exists");
        //TODO: agregar role user

        String passwordHash = encoder.encode(dto.getPassword());
        UserEntity user = new UserEntity(dto, passwordHash);

        user = userService.save(user);
        return mapperToLogged(user.getEmail(), user.getRoles(), user.getId());
    }

    public UserLoggedDto loginUser(LoginDto dto){
        UserEntity user = userService.findByEmail(dto.getEmail(), UserEntity.class)
            .orElseThrow(() -> CustomError.notFound("email or password is not valid"));

        Boolean matchPassword = encoder.matches(dto.getPassword(), user.getPassword());
        if( !matchPassword ) throw CustomError.badRequest("email or password is not valid");

        return mapperToLogged(user.getEmail(), user.getRoles(), user.getId());
    }


    private UserLoggedDto mapperToLogged(String email, List<RoleEntity> rolesEntities, Long userId){
        List<String> roles = rolesEntities.stream()
            .map(r -> r.getRole()).toList();

        CustomUserAuthDto auth = new CustomUserAuthDto(email, userId, roles);
        String token = jwtService.createJwt(auth);

        return new UserLoggedDto(email, userId, roles, token);
    }

}
