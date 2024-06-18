package com.brasens.repository;

import com.brasens.dtos.Alert;
import com.brasens.dtos.Downtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DowntimeRepository extends JpaRepository<Downtime, UUID> {
}
