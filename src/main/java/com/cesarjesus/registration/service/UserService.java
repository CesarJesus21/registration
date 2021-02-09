package com.cesarjesus.registration.service;

import com.cesarjesus.registration.entity.ConfirmationTokenEntity;
import com.cesarjesus.registration.entity.UserEntity;
import com.cesarjesus.registration.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {



    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User ".concat(email).concat(" not found")));
    }

    public String signUpUser(UserEntity userEntity) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userEntity.getEmail());

        if (userEntityOptional.isPresent()) {
            UserEntity userEntitySaved = userEntityOptional.get();
            if(userEntitySaved.isEnabled())
                throw new IllegalStateException("email already taken");

            userEntity.setId(userEntitySaved.getId());
        }

        String encodedPassword = bCryptPasswordEncoder.encode(userEntity.getPassword());

        userEntity.setPassword(encodedPassword);

        userRepository.save(userEntity);

        String token = UUID.randomUUID().toString();

        ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                userEntity
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

    public List<UserEntity> getUsers(){
        return this.userRepository.findAll();
    }

    public Optional<UserEntity> findByEmail(String emial){
        return this.userRepository.findByEmail(emial);
    }

}
