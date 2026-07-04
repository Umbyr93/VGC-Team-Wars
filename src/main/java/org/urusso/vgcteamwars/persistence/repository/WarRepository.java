package org.urusso.vgcteamwars.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.urusso.vgcteamwars.persistence.model.WarEntity;

public interface WarRepository extends JpaRepository<WarEntity,Long> {
}
