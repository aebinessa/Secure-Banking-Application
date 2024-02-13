package com.example.KFHAuto.SecureBankingSystem.controller.userController;


import com.example.KFHAuto.SecureBankingSystem.bo.user.GetUserRequest;
import com.example.KFHAuto.SecureBankingSystem.entity.UserEntity;

import com.example.KFHAuto.SecureBankingSystem.service.admin.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;
    public AdminController(AdminService adminService){
        this.adminService=adminService;

    }
    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getUsers(){
        List<UserEntity> users = adminService.getUsers();
        return ResponseEntity.ok().body(users);
    }
    @GetMapping("/user")
    public ResponseEntity<GetUserRequest> getUser(@RequestParam int id){
        GetUserRequest user = adminService.getUser(id);
        return ResponseEntity.ok().body(user);




    }
    @DeleteMapping("delete-user")
    public ResponseEntity<Void> deleteUser(@RequestParam int id){
        adminService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}