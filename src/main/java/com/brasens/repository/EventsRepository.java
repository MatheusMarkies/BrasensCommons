package com.brasens.repository;

import java.util.UUID;

import com.brasens.dtos.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Events, UUID> {

}
