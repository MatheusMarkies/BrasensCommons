package com.brasens.repository;

import com.brasens.dtos.Alert;
import com.brasens.dtos.CriticalValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CriticalValuesRepository  extends JpaRepository<CriticalValues, UUID> {

}
