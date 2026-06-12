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
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static com.kjug.boottask.Resources.RegistrationResource;
import static com.kjug.boottask.Resources.LoginResource;
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
        var resource = new LoginResource("john","pass123343534");
        var payload = new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(resource);
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
                .andReturn();
    }
    @Test
    public void shouldGetMessageTest() throws Exception {
        mockMvc.perform(get("/api/message/test")
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON_VALUE)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }
}
