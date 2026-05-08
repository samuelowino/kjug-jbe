package com.kjug.boottask;
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

}
