package com.cesarjesus.registration.repository;

import com.cesarjesus.registration.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
        Optional<UserEntity> findByEmail(String email);


        @Transactional
        @Modifying
        @Query("UPDATE UserEntity a SET a.enabled = TRUE WHERE a.email = ?1")
        int enableAppUser(String email);
}
