package com.server_identity.repository;

import com.server_identity.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserCredentials, UUID> {
    boolean existsByName(String name);

    Optional<UserCredentials> findByName(String username);
}
