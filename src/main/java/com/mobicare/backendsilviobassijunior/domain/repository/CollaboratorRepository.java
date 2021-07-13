package com.mobicare.backendsilviobassijunior.domain.repository;

import com.mobicare.backendsilviobassijunior.domain.model.Collaborator;
import com.mobicare.backendsilviobassijunior.domain.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface CollaboratorRepository extends JpaRepository<Collaborator, Long>{
	
	Optional<Collaborator> findByCpf(String cpf);
	
	Optional<Collaborator> findByDateOfBirth(OffsetDateTime dateOfBirth);

	Optional<Collaborator> findByEmail(String email);

	@Query("select count(c) from Collaborator c")
	Long countCollaborators();

	@Query("select count(c) from Collaborator c where c.sector = :sector")
	Long countCollaboratorsBySector(@Param("sector") Sector sector);

	@Query("select count(c) from Collaborator c where c.age > :age")
	Long countCollaboratorByAgeSenior(@Param("age") Integer age);

	@Query("select count(c) from Collaborator c where c.age < :age")
	Long countCollaboratorByAgeYoung(@Param("age") Integer age);
	
}
