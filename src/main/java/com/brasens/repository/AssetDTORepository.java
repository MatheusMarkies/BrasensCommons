package com.brasens.repository;

import com.brasens.dtos.Asset;
import com.brasens.dtos.AssetSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssetDTORepository extends JpaRepository<AssetSummaryDTO, UUID> {
    Optional<AssetSummaryDTO> findByName(String name);
    Optional<AssetSummaryDTO> findByKey(String key);
}
