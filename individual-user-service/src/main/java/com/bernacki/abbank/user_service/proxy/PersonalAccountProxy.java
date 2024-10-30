package com.bernacki.abbank.user_service.proxy;

import com.bernacki.abbank.user_service.dto.PersonalAccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "personal-account-service")
public interface PersonalAccountProxy {

    @GetMapping("${personal-account-service-endpoint.accList}{id}")
    CollectionModel<PersonalAccountDTO> getUserAccounts(@PathVariable long id);

    @PostMapping("/${service-url.accAddEndpoint}/{userId}")
    void addNewAccountForUser(@PathVariable long userId);
}
