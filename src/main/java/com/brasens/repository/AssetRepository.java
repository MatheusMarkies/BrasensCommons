package com.brasens.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.brasens.dtos.Asset;
import com.brasens.dtos.AssetSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, UUID> {

    // Corrected path to AssetSummaryDTO
    @Query("SELECT new com.brasens.dtos.AssetSummaryDTO(" +
            "a.id, a.name, a.key, a.rpm, a.location, a.isRigidBase, a.type, a.power, a.pasNumber, " +
            "a.lastCommunication, a.added, a.level, lt.location, o.name, b.identifier, dt.assetState) " +
            "FROM Asset a " +
            "LEFT JOIN a.locationTree lt " +
            "LEFT JOIN a.organization o " +
            "LEFT JOIN a.bearing b " +
            "LEFT JOIN a.downtime dt " +
            "WHERE a.name = ?1")
    Optional<AssetSummaryDTO> findAssetSummaryDTOByName(String name);

    // Corrected path to AssetSummaryDTO
    @Query("SELECT new com.brasens.dtos.AssetSummaryDTO(" +
            "a.id, a.name, a.key, a.rpm, a.location, a.isRigidBase, a.type, a.power, a.pasNumber, " +
            "a.lastCommunication, a.added, a.level, lt.location, o.name, b.identifier, dt.assetState) " +
            "FROM Asset a " +
            "LEFT JOIN a.locationTree lt " +
            "LEFT JOIN a.organization o " +
            "LEFT JOIN a.bearing b " +
            "LEFT JOIN a.downtime dt " +
            "WHERE a.key = ?1")
    Optional<AssetSummaryDTO> findAssetSummaryDTOByKey(String key);

    // Corrected path to AssetSummaryDTO
    @Query("SELECT new com.brasens.dtos.AssetSummaryDTO(" +
            "a.id, a.name, a.key, a.location, a.rpm, a.type, l.location, a.level) " +
            "FROM Asset a JOIN a.locationTree l")
    List<AssetSummaryDTO> findAllAssetSummaries();

    Optional<Asset> findByName(String name);

    Optional<Asset> findByKey(String key);
}