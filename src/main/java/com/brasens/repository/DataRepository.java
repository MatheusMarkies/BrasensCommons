package com.brasens.repository;

import java.util.List;
import java.util.UUID;

import com.brasens.dtos.Data;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DataRepository extends JpaRepository<Data, UUID> {

    List<Data> findByKey(String key);

}