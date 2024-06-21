package com.brasens.repository;

import com.brasens.dtos.Alert;
import com.brasens.dtos.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface AlertRepository extends JpaRepository<Alert, UUID> {
    List<Alert> findByKey(String key);
}