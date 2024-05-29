package com.brasens.repository;

import com.brasens.dtos.VibrationPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface VibrationPackageRepository  extends JpaRepository<VibrationPackage, UUID> {
    List<VibrationPackage> findByKey(String key);
}
