package com.brasens.repository;

import com.brasens.dtos.Alert;
import com.brasens.dtos.VibrationSensorReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VibrationSensorReadingRepository extends JpaRepository<VibrationSensorReading, UUID> {
}
