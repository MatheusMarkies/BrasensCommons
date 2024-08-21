package com.brasens.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.brasens.dtos.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, UUID> {

    Optional<Asset> findByName(String name);
    Optional<Asset> findByKey(String key);

}