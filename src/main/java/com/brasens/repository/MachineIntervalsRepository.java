package com.brasens.repository;

import com.brasens.dtos.MachineIntervals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MachineIntervalsRepository extends JpaRepository<MachineIntervals, UUID> {

}
