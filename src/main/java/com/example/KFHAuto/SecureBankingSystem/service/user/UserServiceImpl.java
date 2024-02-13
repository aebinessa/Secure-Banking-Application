package com.example.KFHAuto.SecureBankingSystem.service.user;

import com.example.KFHAuto.SecureBankingSystem.bo.user.CreateUserRequest;
import com.example.KFHAuto.SecureBankingSystem.bo.user.GetUserRequest;
import com.example.KFHAuto.SecureBankingSystem.bo.user.UpdateUserRequest;
import com.example.KFHAuto.SecureBankingSystem.entity.AccountEntity;
import com.example.KFHAuto.SecureBankingSystem.entity.UserEntity;
import com.example.KFHAuto.SecureBankingSystem.repository.AccountRepository;
import com.example.KFHAuto.SecureBankingSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void saveUser(CreateUserRequest createUserRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(createUserRequest.getUsername());
        userEntity.setPhoneNumber(createUserRequest.getPhoneNumber());
        userEntity.setPassword(createUserRequest.getPassword());
        userEntity.setEmail(createUserRequest.getEmail());
        userRepository.save(userEntity);
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUser(userEntity);
    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest, int id) {
        UserEntity userEntity = userRepository.getById(id);
        userEntity.setUsername(updateUserRequest.getUsername());
        userEntity.setPhoneNumber(updateUserRequest.getPhoneNumber());
        userEntity.setEmail(updateUserRequest.getEmail());
        userEntity.setAddress(updateUserRequest.getAddress());
        userEntity.setPassword(updateUserRequest.getPassword());
        userRepository.save(userEntity);

    }


    @Override
    public GetUserRequest getUser(int id) {
        UserEntity userEntity = userRepository.getById(id);
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setUsername(userEntity.getUsername());
        getUserRequest.setPassword(userEntity.getPassword());
        getUserRequest.setEmail(userEntity.getEmail());
        getUserRequest.setAddress(userEntity.getAddress());
        getUserRequest.setPhoneNumber(userEntity.getPhoneNumber());
        getUserRequest.setId(userEntity.getId());
        return getUserRequest;
    }

    @Override
    public List<UserEntity> allUsers() {
        return null;
    }
}