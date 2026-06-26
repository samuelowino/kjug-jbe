package com.kjug.boottask;
import jakarta.validation.GroupSequence;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.HashMap;
import java.util.Map;
import static com.kjug.boottask.Resources.UserResource;
import static com.kjug.boottask.Resources.RegistrationResource;
import static com.kjug.boottask.Resources.SessionResource;
import static com.kjug.boottask.Resources.LoginResource;
import static com.kjug.boottask.Resources.LogoutResource;
@Log
@Validated
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<UserResource> registerUser(
            @RequestBody RegistrationResource registrationDetails
    ){
        var resource = authService.registerUser(registrationDetails);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }
    @GetMapping("/message/{value}")
    public ResponseEntity<ApiResponse<String>> getMessage(
            @PathVariable("value") String value,
            UriComponentsBuilder uriBuilder) {
        if (value.contentEquals("test")) throw new NullPointerException();
        var exception = "File not found error, pass correct file name";
        var apiResponse = new ApiResponse<String>(
                "hello",
                "Successful",
                exception
        );
        Map<String, Object> links = new HashMap<>();
        links.put("holiday-message", "/message/holiday");
        links.put("love-message", "/message/love-quotes");
        var uri = uriBuilder
                .path("/message/holiday")
                .build(links);
        return ResponseEntity
                .created(uri) //201
                .body(apiResponse);
    }
    @PostMapping("/login")
    public ResponseEntity<SessionResource> login(
            @RequestBody @Valid  LoginResource credentials
            ){
        var sessionId = authService.login(credentials);
        if (sessionId.isEmpty())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else
            return ResponseEntity.ok(sessionId.get());
    }
    @PostMapping("/logout")
    public ResponseEntity<LogoutResource> logout(
            @RequestHeader(value = "Authorization")
            @NotBlank(message = "session id must not be blank")
            @NotNull(message = "session id must not be null")
            String sessionId
    ) {
        log.info("session id is " + sessionId);
        boolean isLoggedOut = authService.logout(sessionId);
        if (isLoggedOut)
            return ResponseEntity.ok(new LogoutResource("Logged out"));
        else return new ResponseEntity<>(new LogoutResource("Log out failed"),
                HttpStatus.UNAUTHORIZED);
    }
    @PasswordMatch(groups = SecondPhase.class) // Runs ONLY in the second phase
    @GroupSequence({FirstPhase.class, SecondPhase.class, UserRegisterDto.class}) // Order of operations
    public class UserRegisterDto {

        @NotBlank(groups = FirstPhase.class) // Runs in the first phase
        private String email;

        @NotBlank(groups = FirstPhase.class)
        @Size(min = 8, message = "Password must be at least 8 characters", groups = FirstPhase.class)
        private String password;

        @NotBlank(groups = FirstPhase.class)
        private String confirmPassword;

        // Getters and Setters...
    }
}
