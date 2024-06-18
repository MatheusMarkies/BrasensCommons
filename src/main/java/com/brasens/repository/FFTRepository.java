package com.brasens.repository;

import com.brasens.dtos.Alert;
import com.brasens.dtos.FFT;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FFTRepository extends JpaRepository<FFT, UUID> {
}
