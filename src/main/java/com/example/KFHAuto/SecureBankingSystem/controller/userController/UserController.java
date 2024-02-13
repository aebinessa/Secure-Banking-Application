package com.example.KFHAuto.SecureBankingSystem.controller.userController;


import com.example.KFHAuto.SecureBankingSystem.bo.auth.CreateSignupRequest;

import com.example.KFHAuto.SecureBankingSystem.bo.user.GetUserRequest;
import com.example.KFHAuto.SecureBankingSystem.bo.user.UpdateUserRequest;
import com.example.KFHAuto.SecureBankingSystem.entity.UserEntity;
import com.example.KFHAuto.SecureBankingSystem.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){

        this.userService = userService;

    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody CreateSignupRequest createSignupRequest){
        try {
            userService.saveUser((createSignupRequest));
        }catch (IllegalArgumentException e ){

            return ResponseEntity.badRequest().body(e.getMessage());
        }
        userService.saveUser(createSignupRequest);
        return ResponseEntity.ok("User has been created !!!");



    }



    @GetMapping("/user-list")
    public List<UserEntity> getAllUsers(){
        return userService.allUsers();
    }
    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest updateUserRequest, @RequestParam int id){
        userService.updateUser(updateUserRequest, id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/get-user")
    public ResponseEntity<GetUserRequest> getUser(@RequestParam int id){
        GetUserRequest getUserRequest = userService.getUser(id);
        return ResponseEntity.ok().body(getUserRequest);
    }
}