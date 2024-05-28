package com.brasens.repository;

import com.brasens.dtos.ClientModel;
import com.brasens.dtos.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    List<Optional<PasswordResetToken>> findByClient(ClientModel client);
}