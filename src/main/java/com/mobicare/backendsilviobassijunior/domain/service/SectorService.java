package com.mobicare.backendsilviobassijunior.domain.service;

import com.mobicare.backendsilviobassijunior.domain.exception.BusinessException;
import com.mobicare.backendsilviobassijunior.domain.exception.EntityInUseException;
import com.mobicare.backendsilviobassijunior.domain.exception.SectorNotFoundException;
import com.mobicare.backendsilviobassijunior.domain.model.Sector;
import com.mobicare.backendsilviobassijunior.domain.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SectorService {

	private static final String DESCRIPTION_EXISTS = "A descrição: %s informada já existe!";

	private static final String SECTOR_IN_USE = 
			"O Setor de código %d não pode ser removido, pois está em uso!";
	
	@Autowired
	private SectorRepository repository;

	@Transactional
	public Sector store(Sector sector) {
		
		Optional<Sector> existingSector = repository.findByDescription(sector.getDescription());
		
		if(existingSector.isPresent() && !existingSector.get().equals(sector)) {
			throw new BusinessException(String.format(DESCRIPTION_EXISTS, sector.getDescription()));
		}
		
		return repository.save(sector);
	}
	
	@Transactional
	public void deleteById(Long sectorId) {
		
		try {
			repository.deleteById(sectorId);
			repository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new SectorNotFoundException(sectorId);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(SECTOR_IN_USE, sectorId));
		}
	}
	
	public Sector findOrFail(Long sectorId) {
		Sector sector = repository.findById(sectorId)
			.orElseThrow(() -> new SectorNotFoundException(sectorId));
		return sector;
	}
}
