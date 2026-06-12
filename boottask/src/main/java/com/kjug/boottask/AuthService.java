package com.kjug.boottask;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.kjug.boottask.Resources.RegistrationResource;
import static com.kjug.boottask.Resources.UserResource;
import static com.kjug.boottask.Resources.SessionResource;
import static com.kjug.boottask.Resources.LoginResource;
@Service
public class AuthService {
    private final UserRepository userRepository;
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserResource registerUser(RegistrationResource registrationDetails){
        var user = registrationDetails.toUser();
        var entity = userRepository.save(user);
        return UserResource.fromUser(entity);
    }
    public Optional<SessionResource> login(LoginResource credentials) {
        // check if user exists
        // compare passwords : unauthorized | authorized
        // repository.existsBy
        var exists = userRepository.existsByUsername(credentials.username());
        if (!exists) return Optional.empty();
        return null;
    }
}
