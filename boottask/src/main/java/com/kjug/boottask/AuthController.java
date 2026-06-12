package com.kjug.boottask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kjug.boottask.Resources.UserResource;
import static com.kjug.boottask.Resources.RegistrationResource;
import static com.kjug.boottask.Resources.SessionResource;
import static com.kjug.boottask.Resources.LoginResource;
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    // Http protocol
    // Client : browser
    // Server : google servers
    // client sends Http request
    // server receives http request
    // server return a http response


    // GET /hotels/location
    // RESPONSE : list of hotels
    // ResponseEntity is a representation of the http response

    // 500 : internal server error
    // 404 : not found
    // message : an error occurred
    // data : list of hotels

    // HttpResponse -> ResponseEntity

    // OOP
    // Functional Programming : lambda : functional interface
    // Imperative : procedural : console
    // Aspect Oriented Programming AOP
    // inject operations in non-specific code

    // function : audit()
    // - log the account being audited : logfile : before audit()
    // - log the audit progress by timestamp : during audit()
    // - log when the audit was completed : timestamp : after audit()
    // - ip of the node that executed the audit : after audit()

    public void saleaudit() {
        // log : starting audi at timestamp
        // log : starting sales audit timestamp
        // log : finished auditing : timestamp
    }
    public void procurementaudit() {
        // log : starting audi at timestamp
        // log : starting sales audit timestamp
        // log : finished auditing : timestamp
    }

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
            @RequestBody LoginResource credentials
            ){
        return null;
    }
}
