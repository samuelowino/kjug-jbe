package com.kjug.boottask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.kjug.boottask.Resources.UserResource;
import static com.kjug.boottask.Resources.RegistrationResource;
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserRepository repository;
    @PostMapping("/register")
    public ResponseEntity<UserResource> registerUser(
            @RequestBody RegistrationResource registrationDetails
    ){
        var user = registrationDetails.toUser();
        var entity = repository.save(user);
        var resource = UserResource.fromUser(entity);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }
}
