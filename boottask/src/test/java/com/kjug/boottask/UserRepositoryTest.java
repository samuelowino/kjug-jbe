package com.kjug.boottask;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Test
    public void shouldPersistUserTest() {
        var testUser = new User(
                "michael",
                "pass12345678",
                "mike@gmail.com");
        var entity = entityManager.persistAndFlush(testUser);
        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isNotNull();
        assertThat(entity.getCreatedAt()).isNotNull();
        assertThat(entity.getLastUpdated()).isNotNull();
        assertThat(entity.getUsername()).isEqualTo(testUser.getUsername());
        assertThat(entity.getPassword()).isEqualTo(testUser.getPassword());
        assertThat(entity.getEmail()).isEqualTo(testUser.getEmail());
    }
}
