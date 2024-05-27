package com.brasens.repository;

import com.brasens.dtos.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientModel, UUID> {

    Optional<ClientModel> findByLogin(String login);
    Optional<ClientModel> findByEmail(String email);

}
