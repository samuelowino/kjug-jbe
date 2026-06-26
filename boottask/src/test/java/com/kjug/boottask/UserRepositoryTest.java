package com.kjug.boottask;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    @DisplayName("checks if repository can find user by name")
    public void shouldFindUserByUsernameTest() {
        var testUser = new User("mark",
                "mark123","mark@email.com");
        var savedUser = userRepository.save(testUser);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("mark");
        assertThat(savedUser.getEmail()).isEqualTo("mark@email.com");
        var entity = userRepository.findByUsername("mark");
        assertThat(entity).isPresent();
        assertThat(entity.get()).isNotNull();
        assertThat(entity.get()).isNotNull();
        assertThat(entity.get().getUsername()).isEqualTo("mark");
        assertThat(entity.get().getEmail()).isEqualTo("mark@email.com");
   }
}
