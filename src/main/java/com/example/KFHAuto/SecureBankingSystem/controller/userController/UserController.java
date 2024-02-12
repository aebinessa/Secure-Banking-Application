package com.example.KFHAuto.SecureBankingSystem.controller.userController;


import com.example.KFHAuto.SecureBankingSystem.bo.user.CreateUserRequest;
import com.example.KFHAuto.SecureBankingSystem.bo.user.UpdateUserRequest;
import com.example.KFHAuto.SecureBankingSystem.entity.UserEntity;
import com.example.KFHAuto.SecureBankingSystem.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){

        this.userService = userService;

    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest createUserRequest){
        try {
            userService.saveUser((createUserRequest));
        }catch (IllegalArgumentException e ){

            return ResponseEntity.badRequest().body(e.getMessage());
        }
        userService.saveUser(createUserRequest);
        return ResponseEntity.ok("User has been created !!!");



    }



    @GetMapping("/user-list")
    public List<UserEntity> getAllUsers(){
        return userService.allUsers();
    }
}