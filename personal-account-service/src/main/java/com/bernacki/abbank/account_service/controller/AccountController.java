package com.bernacki.abbank.account_service.controller;

import com.bernacki.abbank.account_service.entity.PersonalAccount;
import com.bernacki.abbank.account_service.repository.AccountRepository;
import com.bernacki.abbank.account_service.service.IBANService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private IBANService ibanService;

    @GetMapping("/{userId}")
    public List<PersonalAccount> getUserAccounts(@PathVariable long userId){
        return accountRepository.findAllByUserId(userId);
    }

    @PostMapping("/add/{userId}")
    public void createNewPersonalAccount(@PathVariable Long userId){
        PersonalAccount account = new PersonalAccount(ibanService.generateIban("PL"),userId);
        accountRepository.save(account);
    }
}
