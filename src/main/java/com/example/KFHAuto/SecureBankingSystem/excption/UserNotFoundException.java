package com.example.KFHAuto.SecureBankingSystem.excption;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String str){
        super(str);
    }
}
