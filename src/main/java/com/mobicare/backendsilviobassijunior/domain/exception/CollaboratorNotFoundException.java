package com.mobicare.backendsilviobassijunior.domain.exception;

public class CollaboratorNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public CollaboratorNotFoundException(String message) {
		super(message);
	}
	
	public CollaboratorNotFoundException(Long collaboratorId) {
		this(String.format("Não existe um registro de colaborador com código %d", collaboratorId));
	}
	
}
