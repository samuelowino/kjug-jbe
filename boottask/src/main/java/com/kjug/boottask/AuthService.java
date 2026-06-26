package com.kjug.boottask;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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
        var user = userRepository.findByUsername(credentials.username());
        if (user.isEmpty()) return Optional.empty();
        if (!user.get().getPassword()
                .contentEquals(credentials.password())) return Optional.empty();
        var sessionId = UUID.randomUUID().toString();
        return Optional.of( new SessionResource(sessionId));
    }
    public boolean logout(String sessionId) {
        return true;
    }
}
