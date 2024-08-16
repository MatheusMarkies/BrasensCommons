package com.brasens.repository;

import com.brasens.dtos.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DataRepository extends JpaRepository<Data, UUID> {
    List<Data> findByKey(String key);
}
