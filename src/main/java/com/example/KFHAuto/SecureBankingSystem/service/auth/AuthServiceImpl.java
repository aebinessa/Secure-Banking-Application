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

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public AuthServiceImpl(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JWTUtil jwtUtil, RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void signup(CreateSignupRequest createSignupRequest) {
        RoleEntity roleEntity= roleRepository.findRoleEntityByTitle(Roles.USER.name()).orElseThrow();

        UserEntity user= new UserEntity();
        user.setUsername(createSignupRequest.getUsername());
        user.setPhoneNumber(createSignupRequest.getPhoneNumber());
        user.setEmail(createSignupRequest.getEmail());


        user.setPassword(bCryptPasswordEncoder.encode(createSignupRequest.getPassword()));
        userRepository.save(user);
    }

    @Override
    public AuthinticationResponse login(CreateLoginRequest createLoginRequest) {
        requiredNonNull(createLoginRequest.getUsername(),"username");
        requiredNonNull(createLoginRequest.getPassword(),"password");

        String username= createLoginRequest.getUsername().toLowerCase();
        String password= createLoginRequest.getPassword();

        authentication(username, password);

        CustomUserDetails userDetails= userDetailsService.loadUserByUsername(username);

        String accessToken = jwtUtil.generateToken(userDetails);

        AuthinticationResponse authinticationResponse = new AuthinticationResponse();
        authinticationResponse.setId(userDetails.getId());
        authinticationResponse.setUsername(userDetails.getUsername());
        authinticationResponse.setRole(userDetails.getRole());
        authinticationResponse.setToken("Bearer "+accessToken);
        return authinticationResponse;
    }

    @Override
    public void logout(LogoutResponse logoutResponse) {
        requiredNonNull(logoutResponse.getToken(),"token");
    }

    private void requiredNonNull(Object obj, String name){
        if(obj == null || obj.toString().isEmpty()){

        }
    }

    private void authentication(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (AuthenticationServiceException e) {
            throw new UserNotFoundException("Incorrect username");
        }
    }
}

