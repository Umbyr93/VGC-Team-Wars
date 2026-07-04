package org.urusso.vgcteamwars.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.urusso.vgcteamwars.persistence.model.TeamEntity;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<TeamEntity,Long> {
    List<TeamEntity> findByNameContainingIgnoreCase(String name);
    Optional<TeamEntity> findByName(String name);
}
