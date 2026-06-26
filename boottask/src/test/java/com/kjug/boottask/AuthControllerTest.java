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
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static com.kjug.boottask.Resources.RegistrationResource;
import static com.kjug.boottask.Resources.LoginResource;
import static com.kjug.boottask.Resources.SessionResource;
@WebMvcTest
@ExtendWith(SpringExtension.class)
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private AuthService authService;
    @Test
    public void shouldRegisterUserTest() throws Exception {
        var registrationResource = new RegistrationResource(
                "joelaustime",
                "password",
                "joelaustine@gmail.com"
        );
        var fakeUserResource = new Resources.UserResource(
                434323L,
                registrationResource.username(),
                registrationResource.email());
        Mockito.when(authService.registerUser(any(RegistrationResource.class)))
                .thenReturn(fakeUserResource);
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
    }
    @Test
    public void shouldLoginUserTest() throws Exception {
        var resource = new LoginResource("samuel","32432423kdfjdlfkd");
        var payload = new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(resource);
        Mockito.when(authService.login(resource))
                        .thenReturn(Optional.of( new SessionResource(
                                "534f8cdd-600f-4c6d-acd0-edd22ee6de55"
                        )));
        mockMvc.perform(
                post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding("UTF-8")
                        .content(payload)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("@.sessionId").exists())
                .andExpect(jsonPath("@.sessionId").isString())
                .andExpect(jsonPath("@.sessionId")
                        .value("534f8cdd-600f-4c6d-acd0-edd22ee6de55"))
                .andReturn();
    }
    @Test
    public void shouldGetMessageTest() throws Exception {
        mockMvc.perform(get("/api/message/hello")
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON_VALUE)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }
    @Test
    public void shouldLogoutTest() throws Exception {
        var sessionId = "Bearer 6df5383d-a7e6-4ced-a414-06ffb2230248";
        Mockito.when(authService.logout(any(String.class)))
                        .thenReturn(true);
        mockMvc.perform(post("/api/logout")
                .header("Authorization", "Bearer " + sessionId)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("@.message").isString())
                .andExpect(jsonPath("@.message").value("Logged out"))
                .andReturn();
    }
}
