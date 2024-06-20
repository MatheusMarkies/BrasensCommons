package com.brasens.repository;

import com.brasens.dtos.Alert;
import com.brasens.dtos.FFTStatisticalValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public interface FFTStatisticalValuesRepository extends JpaRepository<FFTStatisticalValues, UUID> {
    @Query("select f from FFT_Statistical_Values f where f.added >= :added")
    List<FFTStatisticalValues> findAllWithAddedAfter(@Param("added") ZonedDateTime added);
}