package com.brasens.repository;

import com.brasens.dtos.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeesRepository extends JpaRepository<Employees, UUID> {
    Optional<Employees> findByLogin(String login);
    Optional<Employees> findByEmail(String email);
}
