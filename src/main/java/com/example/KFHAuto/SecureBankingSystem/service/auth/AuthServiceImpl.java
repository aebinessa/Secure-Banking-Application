package com.example.KFHAuto.SecureBankingSystem.service.auth;

import com.example.KFHAuto.SecureBankingSystem.bo.auth.AuthinticationResponse;
import com.example.KFHAuto.SecureBankingSystem.bo.auth.CreateLoginRequest;

import com.example.KFHAuto.SecureBankingSystem.bo.auth.CreateSignupRequest;
import com.example.KFHAuto.SecureBankingSystem.bo.auth.LogoutResponse;
import com.example.KFHAuto.SecureBankingSystem.bo.customUserDetails.CustomUserDetails;

import com.example.KFHAuto.SecureBankingSystem.config.JWTUtil;
import com.example.KFHAuto.SecureBankingSystem.entity.RoleEntity;
import com.example.KFHAuto.SecureBankingSystem.entity.UserEntity;
import com.example.KFHAuto.SecureBankingSystem.excption.UserNotFoundException;
import com.example.KFHAuto.SecureBankingSystem.repository.RoleRepository;
import com.example.KFHAuto.SecureBankingSystem.repository.UserRepository;
import com.example.KFHAuto.SecureBankingSystem.util.enums.Roles;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JWTUtil jwtUtil, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }



    @Override
    public void signup(CreateSignupRequest createSignupRequest) {
        RoleEntity roleEntity= roleRepository.findRoleEntityByTitle(Roles.user.name())
                .orElseThrow();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(createSignupRequest.getUsername());
        userEntity.setAddress(createSignupRequest.getAddress());
        userEntity.setEmail(createSignupRequest.getEmail());
        userEntity.setPhoneNumber(createSignupRequest.getPhoneNumber());
        userEntity.setRoles(roleEntity);
        userEntity.setPassword(bCryptPasswordEncoder.encode(createSignupRequest.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public AuthinticationResponse login(CreateLoginRequest createLoginRequest) {
        if (createLoginRequest.getPassword() == null || createLoginRequest.getUsername() == null){
            throw new NullPointerException();
        }
        String username = createLoginRequest.getUsername().toLowerCase();
        String password = createLoginRequest.getPassword();

        authentication(username, password);

        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String accessToken = jwtUtil.generateToken(userDetails);

        AuthinticationResponse response = new AuthinticationResponse();
        response.setId(userDetails.getId());
        response.setUsername(userDetails.getUsername());
        response.setRole(userDetails.getRole());
        response.setToken("Bearer " + accessToken);
        return response;
    }

    @Override
    public void logout(LogoutResponse logoutResponse) {

    }


    private void authentication(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (AuthenticationServiceException e) {
            throw new UserNotFoundException("Incorrect username");
        }
    }
}

