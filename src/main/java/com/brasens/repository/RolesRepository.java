package com.brasens.repository;

import com.brasens.dtos.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface RolesRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRole(String role);
}