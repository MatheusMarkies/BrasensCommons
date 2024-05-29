package com.brasens.repository;

import com.brasens.dtos.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {
    Optional<Privilege> findByName(String name);
}
