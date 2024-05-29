package com.brasens.repository;

import com.brasens.dtos.FFTComponents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FFTComponentsRepository extends JpaRepository<FFTComponents, UUID> {
}
