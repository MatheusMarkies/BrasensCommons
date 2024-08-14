package com.brasens.repository;

import com.brasens.dtos.History;
import com.brasens.dtos.LocationTree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LocationTreeRepository extends JpaRepository<LocationTree, UUID> {
    @Override
    Optional<LocationTree> findById(UUID uuid);
    Optional<LocationTree> findByLocation(String location);
}
