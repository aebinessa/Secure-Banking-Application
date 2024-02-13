package com.example.KFHAuto.SecureBankingSystem.service.user;


import com.example.KFHAuto.SecureBankingSystem.bo.auth.CreateSignupRequest;

import com.example.KFHAuto.SecureBankingSystem.bo.user.GetUserRequest;
import com.example.KFHAuto.SecureBankingSystem.bo.user.UpdateUserRequest;
import com.example.KFHAuto.SecureBankingSystem.entity.UserEntity;

import java.util.List;

public interface UserService {

    void saveUser(CreateSignupRequest createSignupRequest);



    void updateUser(UpdateUserRequest updateUserRequest, int id);


    List<UserEntity> allUsers();
    GetUserRequest getUser(int id);
}





