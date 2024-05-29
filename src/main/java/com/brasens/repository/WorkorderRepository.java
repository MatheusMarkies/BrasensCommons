package com.brasens.repository;

import com.brasens.dtos.Workorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface WorkorderRepository extends JpaRepository<Workorder, UUID> {

    Optional<Workorder> findByName(String name);
    Optional<Workorder> findByOwner(String owner);
    Optional<Workorder> findByAsset(String asset);

}
