package com.brasens.repository;

import com.brasens.dtos.FFTModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FFTRepository extends JpaRepository<FFTModel, UUID> {
	Optional<FFTModel> findByKey(String key);
}
