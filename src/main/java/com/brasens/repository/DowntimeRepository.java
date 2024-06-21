package com.brasens.repository;

import com.brasens.dtos.Alert;
import com.brasens.dtos.AssetTree;
import com.brasens.dtos.Data;
import com.brasens.dtos.Downtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DowntimeRepository extends JpaRepository<Downtime, UUID> {
    Optional<Downtime> findByKey(String key);
}
