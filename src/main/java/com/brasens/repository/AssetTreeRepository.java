package com.brasens.repository;

import com.brasens.dtos.Asset;
import com.brasens.dtos.AssetTree;
import com.brasens.dtos.Bearings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AssetTreeRepository extends JpaRepository<AssetTree, UUID> {
    Optional<AssetTree> findByKey(String key);
}
