package com.example.KFHAuto.SecureBankingSystem.service.user;


import com.example.KFHAuto.SecureBankingSystem.bo.user.CreateUserRequest;
import com.example.KFHAuto.SecureBankingSystem.bo.user.UpdateUserRequest;
import com.example.KFHAuto.SecureBankingSystem.entity.UserEntity;

import java.util.List;

public interface UserService {

    void saveUser(CreateUserRequest createUserRequest);

  void updateUser(UpdateUserRequest updateUserRequest);


    List<UserEntity> allUsers();
}





