package com.brasens.repository;

import com.brasens.dtos.Alert;
import com.brasens.dtos.FFTStatisticalValues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FFTStatisticalValuesRepository extends JpaRepository<FFTStatisticalValues, UUID> {
}
