package com.mobicare.backendsilviobassijunior.domain.repository;

import com.mobicare.backendsilviobassijunior.domain.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface SectorRepository extends JpaRepository<Sector, Long>{
	
	@Query("select distinct(s) from Sector s join fetch s.collaborators c")
	List<Sector> findAll();

	Optional<Sector> findByDescription(String description);

	@Query("select max(s.updateAt) from Sector s")
	OffsetDateTime getLastUpdateDate();

}
