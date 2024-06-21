package com.brasens.repository;

import com.brasens.dtos.Employees;
import com.brasens.dtos.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    List<Optional<PasswordResetToken>> findByClient(Employees client);
}