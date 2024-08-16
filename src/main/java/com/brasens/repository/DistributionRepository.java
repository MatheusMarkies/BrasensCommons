package com.brasens.repository;

import com.brasens.dtos.Distribution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DistributionRepository extends JpaRepository<Distribution, UUID> {
}
