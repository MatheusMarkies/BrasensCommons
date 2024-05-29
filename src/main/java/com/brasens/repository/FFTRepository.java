package com.brasens.repository;

import com.brasens.dtos.FFTModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface FFTRepository extends JpaRepository<FFTModel, UUID> {
	Optional<FFTModel> findByKey(String key);
}
