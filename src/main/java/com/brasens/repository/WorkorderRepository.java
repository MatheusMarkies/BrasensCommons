package com.brasens.repository;

import com.brasens.dtos.Workorder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkorderRepository extends JpaRepository<Workorder, UUID> {
}
