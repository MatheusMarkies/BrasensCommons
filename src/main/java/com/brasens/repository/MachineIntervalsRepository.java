package com.brasens.repository;

import com.brasens.dtos.MachineIntervals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MachineIntervalsRepository extends JpaRepository<MachineIntervals, UUID> {

}
