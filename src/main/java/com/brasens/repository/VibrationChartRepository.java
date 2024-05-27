package com.brasens.repository;

import com.brasens.dtos.VibrationChart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VibrationChartRepository extends JpaRepository<VibrationChart, UUID> {
	Optional<VibrationChart> findByKey(String key);
}
