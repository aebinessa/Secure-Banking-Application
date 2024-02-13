package com.example.KFHAuto.SecureBankingSystem.repository;


import com.example.KFHAuto.SecureBankingSystem.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

}