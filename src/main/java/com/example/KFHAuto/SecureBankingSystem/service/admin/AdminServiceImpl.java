package com.example.KFHAuto.SecureBankingSystem.service.admin;

import com.example.KFHAuto.SecureBankingSystem.bo.user.GetUserRequest;
import com.example.KFHAuto.SecureBankingSystem.entity.UserEntity;
import com.example.KFHAuto.SecureBankingSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users;
    }

    @Override
    public GetUserRequest getUser(int id) {
        UserEntity userEntity = userRepository.getById(id);
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setAddress(userEntity.getAddress());
        getUserRequest.setEmail(userEntity.getEmail());
        getUserRequest.setPassword(userEntity.getPassword());
        getUserRequest.setPhoneNumber(userEntity.getPhoneNumber());
        getUserRequest.setUsername(userEntity.getUsername());
        getUserRequest.setId(userEntity.getId());
        return getUserRequest;
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}