package com.brasens.repository;

import com.brasens.dtos.Alert;
import com.brasens.dtos.Bearings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BearingsRepository extends JpaRepository<Bearings, UUID> {
}
