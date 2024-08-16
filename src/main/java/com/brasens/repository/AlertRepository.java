package com.brasens.repository;

import com.brasens.dtos.AlertTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface AlertRepository extends JpaRepository<AlertTarget, UUID> {
    List<AlertTarget> findByKey(String key);
}