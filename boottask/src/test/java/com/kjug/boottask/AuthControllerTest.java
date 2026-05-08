package com.kjug.boottask;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@WebMvcTest
@ExtendWith(SpringExtension.class)
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private UserRepository repository;
    @Test
    public void shouldRegisterUserTest() throws Exception {
        var registrationResource = new Resources.RegistrationResource(
                "joelaustime",
                "password",
                "joelaustine@gmail.com"
        );
        var createdAt = LocalDateTime.now();
        var lastUpdated = LocalDateTime.now();
        var fakeEntity = new User(434323L,
                registrationResource.username(),
                registrationResource.password(),
                registrationResource.email(),
                createdAt,
                lastUpdated);
        Mockito.when(repository.save(any(User.class)))
                .thenReturn(fakeEntity);
        var payload = new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(registrationResource);
        mockMvc.perform(
                post("/api/register")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(payload)
                        .characterEncoding("UTF-8")
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("@.id").exists())
                .andExpect(jsonPath("@.id").isNumber())
                .andExpect(jsonPath("@.username").exists())
                .andExpect(jsonPath("@.username").value(registrationResource.username()))
                .andExpect(jsonPath("@.email").value(registrationResource.email()))
                .andReturn();

                // {"id":long,"username":"string","email":"string"}

    }
}
