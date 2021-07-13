package com.mobicare.backendsilviobassijunior.domain.exception;

public class SectorNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public SectorNotFoundException(String message) {
		super(message);
	}
	
	public SectorNotFoundException(Long sectorId) {
		this(String.format("Não existe um registro de setor com código %d", sectorId));
	}
	
}
