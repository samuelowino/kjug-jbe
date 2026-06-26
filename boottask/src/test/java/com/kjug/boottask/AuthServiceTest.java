package com.kjug.boottask;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Optional;
import static com.kjug.boottask.Resources.RegistrationResource;
import static com.kjug.boottask.Resources.LoginResource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @InjectMocks
    private AuthService authService;
    @Mock
    private UserRepository userRepository;
    @Test
    public void shouldRegisterUserTest(){
        var registrationRes = new RegistrationResource(
                "john","pass3323423",
                "john@gmail.com"
        );
        var fakeUserEntity = new User(434332L,
                registrationRes.username(),
                "23232pass",
                registrationRes.email(),
                LocalDateTime.now(),
                LocalDateTime.now());
        when(userRepository.save(any(User.class)))
                .thenReturn(fakeUserEntity);
        var userRes = authService.registerUser(registrationRes);
        assertThat(userRes).isNotNull();
        assertThat(userRes.username()).isEqualTo(registrationRes.username());
        assertThat(userRes.email()).isEqualTo(registrationRes.email());
    }
    @Test
    public void shouldLoginUserTest() {
        var loginRes = new LoginResource("john","3234353pass");
        Mockito.when(userRepository.findByUsername(any(String.class)))
                .thenReturn(Optional.of( new User("john",loginRes.password(),"john@gmail.com")));
        var sessionRes = authService.login(loginRes);
        assertThat(sessionRes).isPresent();
        assertThat(sessionRes.get().sessionId()).isNotEmpty();
        assertThat(sessionRes.get().sessionId()).isNotBlank();
    }
    @Test
    public void shouldFailLoginIfUserDoesNotExistTest() {
        var loginRes = new LoginResource("john","3234353pass");
        Mockito.when(userRepository.findByUsername(any(String.class)))
                .thenReturn(Optional.empty());
        var sessionRes = authService.login(loginRes);
        assertThat(sessionRes).isEmpty();
    }
}
