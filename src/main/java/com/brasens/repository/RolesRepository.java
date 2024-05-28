package com.brasens.repository;

import com.brasens.dtos.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RolesRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRole(String role);
}