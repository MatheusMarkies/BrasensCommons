package com.brasens.repository;

import com.brasens.dtos.FFTComponents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FFTComponentsRepository extends JpaRepository<FFTComponents, UUID> {
}
