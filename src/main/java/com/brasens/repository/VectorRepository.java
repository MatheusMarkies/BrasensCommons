package com.brasens.repository;

import com.brasens.dtos.Vector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VectorRepository extends JpaRepository<Vector, UUID> {
}
