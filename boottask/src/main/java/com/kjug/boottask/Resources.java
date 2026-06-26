package com.kjug.boottask;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public interface Resources {
    record RegistrationResource(
            String username,
            String password,
            String email
    ) {
        public User toUser(){
            return new User(username, password, email);
        }
    }
    record UserResource(
            Long id,
            String username,
            String email
    ){
        public static UserResource fromUser(User entity){
            return new UserResource(
                    entity.getId(),
                    entity.getUsername(),
                    entity.getEmail());
        }
    }
    record LoginResource(
            @NotNull(message = "Username must not be null")
            @NotBlank(message = "Username must not be blank")
            String username,
            @NotNull
            @Size(min = 8, max = 100, message = "password must be more than 8 characters")
            String password
    ){ }
    record SessionResource(
            String sessionId
    ){}
    record LogoutResource(
            String message
    ) {}
}
