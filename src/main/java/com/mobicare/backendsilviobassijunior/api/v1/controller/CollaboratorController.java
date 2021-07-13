package com.mobicare.backendsilviobassijunior.api.v1.controller;

import com.mobicare.backendsilviobassijunior.api.v1.assembler.CollaboratorDisassembler;
import com.mobicare.backendsilviobassijunior.api.v1.assembler.CollaboratorModelAssembler;
import com.mobicare.backendsilviobassijunior.api.v1.assembler.CollaboratorModelResumeAssembler;
import com.mobicare.backendsilviobassijunior.api.v1.model.CollaboratorModel;
import com.mobicare.backendsilviobassijunior.api.v1.model.input.CollaboratorInput;
import com.mobicare.backendsilviobassijunior.domain.model.Collaborator;
import com.mobicare.backendsilviobassijunior.domain.service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/v1/collaborators")
public class CollaboratorController {

	@Autowired
	private CollaboratorService service;
	
	@Autowired
	private CollaboratorModelAssembler assembler;
	
	@Autowired
	private CollaboratorModelResumeAssembler resumeAssembler;
	
	@Autowired
	private CollaboratorDisassembler disassembler;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CollaboratorModel store(@RequestBody @Valid CollaboratorInput collaboratorInput){
		
		Collaborator collaborator = disassembler.toDomainModel(collaboratorInput);
		
		return assembler.toResource(service.store(collaborator));
	}
	
	@PutMapping("/{collaboratorId}")
	public CollaboratorModel update(@RequestBody @Valid CollaboratorInput collaboratorInput, @PathVariable Long collaboratorId){
		
		Collaborator currentCollaborator = service.findOrFail(collaboratorId);		
	
		disassembler.copyToDomainModel(collaboratorInput, currentCollaborator);

		currentCollaborator = service.store(currentCollaborator);
		
		return assembler.toResource(currentCollaborator);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{collaboratorId}")
	public CollaboratorModel findById(@PathVariable Long collaboratorId){
		Collaborator collaborator = service.findOrFail(collaboratorId);
		return assembler.toResource(collaborator);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{collaboratorId}")
	public void deleteById(@PathVariable Long collaboratorId){
		service.deleteById(collaboratorId);
	}


}
