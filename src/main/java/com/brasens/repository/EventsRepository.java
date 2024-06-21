package com.brasens.repository;

import java.util.List;
import java.util.UUID;

import com.brasens.dtos.Alert;
import com.brasens.dtos.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<Events, UUID> {
    List<Events> findByKey(String key);
}
