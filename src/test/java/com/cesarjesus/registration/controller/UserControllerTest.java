package com.cesarjesus.registration.controller;

import com.cesarjesus.registration.entity.RoleEntity;
import com.cesarjesus.registration.entity.UserEntity;
import com.cesarjesus.registration.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private List<UserEntity> userEntityList;

    @BeforeEach
    void setUsers(){
        this.userEntityList = new ArrayList<>();
        this.userEntityList.add(new UserEntity("Cesar Jesus","Gutierrez","cesar_jgg@outlook.com","password",new ArrayList<>(Arrays.asList( new RoleEntity("1", "ROLE_USER")))));
        this.userEntityList.add(new UserEntity("Cesar Jesus2","Gutierrez2","cesar2_jgg@outlook.com","password",new ArrayList<>(Arrays.asList( new RoleEntity("1", "ROLE_USER")))));
    }

    @Test
    void getUsers() throws Exception{

        when(userService.getUsers()).thenReturn(userEntityList);

        MvcResult mvcResult = this.mockMvc.perform(get("/users")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}