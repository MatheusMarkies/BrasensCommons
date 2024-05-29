package com.brasens.repository;

import com.brasens.dtos.VibrationChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface VibrationChartRepository extends JpaRepository<VibrationChart, UUID> {
	Optional<VibrationChart> findByKey(String key);
}
