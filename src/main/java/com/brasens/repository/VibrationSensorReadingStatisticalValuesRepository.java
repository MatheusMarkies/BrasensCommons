package com.brasens.repository;

import com.brasens.dtos.Alert;
import com.brasens.dtos.VibrationSensorReadingStatisticalValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public interface VibrationSensorReadingStatisticalValuesRepository extends JpaRepository<VibrationSensorReadingStatisticalValues, UUID> {
    @Query("select v from VibrationSensorReadingStatisticalValues v where v.added >= :added")
    List<VibrationSensorReadingStatisticalValues> findAllWithAddedAfter(@Param("added") ZonedDateTime added);
}
