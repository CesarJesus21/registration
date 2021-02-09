package com.cesarjesus.registration.controller;

import com.cesarjesus.registration.dto.RegistrationDto;
import com.cesarjesus.registration.service.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService registrationService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void registerSuccess() throws Exception{

        String token = UUID.randomUUID().toString();
        RegistrationDto registrationDto = new RegistrationDto("Cesar Jesus", "Gutierrez", "cesar_jgg@outlook.com", "some_password");
        when(registrationService.register(registrationDto)).thenReturn(token);

        MvcResult mvcResult = this.mockMvc.perform(post("/registration")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsBytes(registrationDto)))
                .andExpect(status().isCreated())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals(result, token);
    }

    @Test
    void confirm() {
    }
}