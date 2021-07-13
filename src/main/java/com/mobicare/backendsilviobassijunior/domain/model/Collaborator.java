package com.mobicare.backendsilviobassijunior.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.time.Period;

@Getter
@Setter

@Entity
@Table(name = "collaborators")
public class Collaborator extends BaseEntity{
	
	private String name;
	private String cpf;
	private String telephone;
	private String email;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private OffsetDateTime dateOfBirth;

	private Integer age;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "sector_id")
	private Sector sector;

	@PrePersist
	public void calculateAge() {
		age = Period.between(dateOfBirth.toLocalDate(), OffsetDateTime.now().toLocalDate()).getYears();
	}
	
}
