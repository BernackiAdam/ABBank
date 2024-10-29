package com.bernacki.abbank.user_service.controller;

import com.bernacki.abbank.user_service.entity.IndividualUser;
import com.bernacki.abbank.user_service.exceptions.UserNotFoundException;
import com.bernacki.abbank.user_service.proxy.PersonalAccountProxy;
import com.bernacki.abbank.user_service.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonalAccountProxy proxy;

    @GetMapping("/all")
    public List<IndividualUser> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<IndividualUser> getUserById(@PathVariable Long id){
        IndividualUser user = userRepository.findById(id)
                .orElseThrow(() ->new UserNotFoundException("Could not find user with id: " + id));
        EntityModel<IndividualUser> entityModel = EntityModel.of(user);
//        Link personalAccountsLink = WebMvcLinkBuilder.linkTo(
//                WebMvcLinkBuilder
//                        .methodOn(PersonalAccountProxy.class).getUserAccounts(id)
//        ).withRel("personal accounts");
        String personalAccountsUrl = String.format(
                "http://localhost:8765/v1/accounts/%d", id
        );
        Link personalAccountsLink = Link.of(personalAccountsUrl).withRel("personal accounts");

        Link selfLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).getUserById(id)
        ).withSelfRel();
        entityModel.add(personalAccountsLink);
        entityModel.add(selfLink);
        return entityModel;
    }
    @PostMapping("/add")
    public ResponseEntity<IndividualUser> addNewIndividualUser(@Valid @RequestBody IndividualUser user){
        IndividualUser savedUser = userRepository.save(user);
        proxy.addNewAccountForUser(savedUser.getId());
        URI location = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(this.getClass())
                        .getUserById(savedUser.getId()))
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteIndividualUser(@PathVariable long id){
        IndividualUser user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Could not find user with id: "+id)
        );
        userRepository.deleteById(id);
    }
    @DeleteMapping("/all")
    public void deleteAll(){
        userRepository.deleteAll();
    }


}
