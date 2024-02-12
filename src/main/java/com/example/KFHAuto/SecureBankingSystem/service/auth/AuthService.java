package com.example.KFHAuto.SecureBankingSystem.service.auth;


import com.example.KFHAuto.SecureBankingSystem.bo.auth.AuthinticationResponse;
import com.example.KFHAuto.SecureBankingSystem.bo.auth.CreateLoginRequest;
import com.example.KFHAuto.SecureBankingSystem.bo.auth.CreateSignupRequest;
import com.example.KFHAuto.SecureBankingSystem.bo.auth.LogoutResponse;

public interface AuthService {
    void signup(CreateSignupRequest createSignupRequest);

    AuthinticationResponse login(CreateLoginRequest createLoginRequest);

    void logout(LogoutResponse logoutResponse);
}