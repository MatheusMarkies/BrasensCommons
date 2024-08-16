package com.brasens.repository;

import com.brasens.dtos.Envelope;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnvelopeRepository extends JpaRepository<Envelope, UUID> {
}
