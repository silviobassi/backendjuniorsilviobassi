package com.mobicare.backendsilviobassijunior.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
public class CollaboratorInput {

	@NotBlank
	private String name;
	
	@CPF
	@NotBlank
	private String cpf;
	
	@NotBlank
	private String telephone;
	
	@Email
	@NotBlank
	private String email;
	
	@NotNull
	private OffsetDateTime dateOfBirth;
	
	@Valid
	@NotNull
	private SectorIdInput sector;
	
}
