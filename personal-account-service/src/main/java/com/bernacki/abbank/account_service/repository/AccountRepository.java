package com.bernacki.abbank.account_service.repository;

import com.bernacki.abbank.account_service.entity.PersonalAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<PersonalAccount, String> {
    List<PersonalAccount> findAllByUserId(long userId);
}
