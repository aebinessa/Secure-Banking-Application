package com.example.KFHAuto.SecureBankingSystem.service.admin;

import com.example.KFHAuto.SecureBankingSystem.bo.user.GetUserRequest;
import com.example.KFHAuto.SecureBankingSystem.entity.UserEntity;

import java.util.List;



public interface AdminService {

    List<UserEntity> getUsers();
    GetUserRequest getUser(int id);

    void deleteUser(int id);
}