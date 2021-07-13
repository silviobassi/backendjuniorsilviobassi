package com.mobicare.backendsilviobassijunior.api.v1.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class CollaboratorModel {

	private Long id;
	
	private String name;
	private String cpf;
	private String telephone;
	private String email;
	private OffsetDateTime dateOfBirth;

	private SectorModelResume sector;

}
