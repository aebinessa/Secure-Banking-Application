package com.example.KFHAuto.SecureBankingSystem.service.user;

import com.example.KFHAuto.SecureBankingSystem.bo.user.CreateUserRequest;
import com.example.KFHAuto.SecureBankingSystem.bo.user.UpdateUserRequest;
import com.example.KFHAuto.SecureBankingSystem.entity.UserEntity;
import com.example.KFHAuto.SecureBankingSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(CreateUserRequest createUserRequest) {
        UserEntity userEntity= new UserEntity();
        userEntity.setUsername(createUserRequest.getUsername());
        userEntity.setPhoneNumber(createUserRequest.getPhoneNumber());
        userEntity.setPassword(createUserRequest.getPassword());
        userEntity.setEmail(createUserRequest.getEmail());
        userRepository.save(userEntity);
    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest) {

    }


    @Override
    public List<UserEntity> allUsers() {
        return null;
    }
}