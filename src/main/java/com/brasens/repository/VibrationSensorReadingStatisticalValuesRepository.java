package com.brasens.repository;

import com.brasens.dtos.Alert;
import com.brasens.dtos.VibrationSensorReadingStatisticalValues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VibrationSensorReadingStatisticalValuesRepository extends JpaRepository<VibrationSensorReadingStatisticalValues, UUID> {
}
