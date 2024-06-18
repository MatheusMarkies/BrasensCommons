package com.brasens.repository;

import com.brasens.dtos.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeesRepository extends JpaRepository<Alert, UUID> {
}
