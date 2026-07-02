package org.urusso.vgcteamwars.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.urusso.vgcteamwars.persistence.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String username);
}
