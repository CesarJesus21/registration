package com.cesarjesus.registration.service;

import com.cesarjesus.registration.dto.RegistrationDto;
import com.cesarjesus.registration.entity.ConfirmationTokenEntity;
import com.cesarjesus.registration.entity.RoleEntity;
import com.cesarjesus.registration.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationDto request) {

        return userService.signUpUser(new UserEntity(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        new ArrayList<>(Arrays.asList(new RoleEntity("1", "ROLE_USER")))
                )
        );
    }


    public String confirmToken(String token) {
        ConfirmationTokenEntity confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUserEntity().getEmail());
        return "confirmed";
    }
}
