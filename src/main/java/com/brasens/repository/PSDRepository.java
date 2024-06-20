package com.brasens.repository;

import com.brasens.dtos.Employees;
import com.brasens.dtos.PSD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PSDRepository extends JpaRepository<PSD, UUID> {
}
