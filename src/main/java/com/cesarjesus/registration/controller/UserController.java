package com.cesarjesus.registration.controller;

import com.cesarjesus.registration.entity.UserEntity;
import com.cesarjesus.registration.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserEntity> getUsers(){
        return this.userService.getUsers();
    }

}

