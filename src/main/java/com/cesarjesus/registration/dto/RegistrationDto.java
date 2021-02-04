package com.cesarjesus.registration.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationDto {


    @NotEmpty(message = "no empty")
    private final String firstName;
    private final String lastName;
    @Email(message = "El emial es incorrecto")
    private final String email;
    private final String password;
}
