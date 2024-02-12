package com.example.KFHAuto.SecureBankingSystem.entity;





import java.util.List;

public interface UserService {

    void saveUser(CreateUserRequest createUserRequest);

    void updateUser(UpdateUserRequest updateUserRequest);

    void updateUserStatus(Long userID, UpdateUserRequest updateUserRequest);

    List<UserEntity> allUsers();
}





