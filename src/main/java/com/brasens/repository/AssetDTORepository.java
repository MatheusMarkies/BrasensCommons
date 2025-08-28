package com.brasens.repository;

import com.brasens.dtos.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssetDTORepository extends JpaRepository<Asset, UUID> {
    Optional<Asset> findByName(String name);
    Optional<Asset> findByKey(String key);
}
