package com.mobicare.backendsilviobassijunior.api.v1.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SectorModel {

	private Long id;
	
	private String description;
	
	private List<CollaboratorModelResume> collaborators;
	
}
