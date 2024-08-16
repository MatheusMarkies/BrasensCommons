package com.brasens.repository;

import com.brasens.dtos.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HistoryRepository extends JpaRepository<History, UUID> {
    @Override
    Optional<History> findById(UUID uuid);

    Optional<History> findByKey(String key);
}
