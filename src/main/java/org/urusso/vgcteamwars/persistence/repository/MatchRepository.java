package org.urusso.vgcteamwars.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.urusso.vgcteamwars.persistence.model.MatchEntity;

public interface MatchRepository extends JpaRepository<MatchEntity,Long> {
}
