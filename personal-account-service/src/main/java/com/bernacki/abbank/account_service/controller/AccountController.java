package com.bernacki.abbank.account_service.controller;

import com.bernacki.abbank.account_service.configuration.UrlConfiguration;
import com.bernacki.abbank.account_service.entity.PersonalAccount;
import com.bernacki.abbank.account_service.repository.AccountRepository;
import com.bernacki.abbank.account_service.service.IBANService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UrlConfiguration urlConfig;

    @Autowired
    private IBANService ibanService;

    @GetMapping("/{userId}")
    public CollectionModel<EntityModel<PersonalAccount>> getUserAccounts(@PathVariable long userId){
        List<PersonalAccount> accounts = accountRepository.findAllByUserId(userId);
        List<EntityModel<PersonalAccount>> accountResources = accounts.stream()
                .map(account -> {
                    EntityModel<PersonalAccount> accountResource = EntityModel.of(account);

                    String individualUserUrl = String.format(
                            "%s/%s/%d",
                            urlConfig.getGatewayUrl(),
                            urlConfig.getUserEndpoint(),
                            userId
                    );

                    String selfUrl= String.format(
                            "%s/%s/%d",
                            urlConfig.getGatewayUrl(),
                            urlConfig.getAccListEndpoint(),
                            userId
                    );
                    Link individualUserLink = Link.of(individualUserUrl).withRel("Individual User");
                    Link selfLink = Link.of(selfUrl).withSelfRel();
                    accountResource.add(individualUserLink);
                    accountResource.add(selfLink);
                    return accountResource;
                }).toList();
        return CollectionModel.of(accountResources);
    }

    @PostMapping("/add/{userId}")
    public void createNewPersonalAccount(@PathVariable Long userId){
        PersonalAccount account = new PersonalAccount(ibanService.generateIban("PL"),userId);
        accountRepository.save(account);
    }
}
