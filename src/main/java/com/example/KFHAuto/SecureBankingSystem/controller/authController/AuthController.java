package com.example.KFHAuto.SecureBankingSystem.controller.authController;




import com.example.KFHAuto.SecureBankingSystem.bo.auth.AuthinticationResponse;
import com.example.KFHAuto.SecureBankingSystem.bo.auth.CreateLoginRequest;
import com.example.KFHAuto.SecureBankingSystem.bo.auth.CreateSignupRequest;
import com.example.KFHAuto.SecureBankingSystem.bo.auth.LogoutResponse;
import com.example.KFHAuto.SecureBankingSystem.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody CreateSignupRequest createSignupRequest){
        authService.signup(createSignupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthinticationResponse> login(@RequestBody CreateLoginRequest createLoginRequest){
        AuthinticationResponse response=authService.login(createLoginRequest);
        HttpStatus status= HttpStatus.OK;
        if (response == null){
            status= HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(response, status);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutResponse logoutResponse){
        authService.logout(logoutResponse);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}