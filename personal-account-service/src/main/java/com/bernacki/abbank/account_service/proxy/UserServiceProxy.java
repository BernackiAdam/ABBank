package com.bernacki.abbank.account_service.proxy;

import com.bernacki.abbank.account_service.dto.IndividualUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service-service")
public interface UserServiceProxy {

    @GetMapping("/v1/user/{id}")
    public IndividualUserDTO getUserById(@PathVariable long id);
}
