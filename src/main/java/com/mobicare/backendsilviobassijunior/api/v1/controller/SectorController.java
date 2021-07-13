package com.mobicare.backendsilviobassijunior.api.v1.controller;

import com.mobicare.backendsilviobassijunior.api.v1.assembler.SectorDisassembler;
import com.mobicare.backendsilviobassijunior.api.v1.assembler.SectorModelAssembler;
import com.mobicare.backendsilviobassijunior.api.v1.assembler.SectorModelResumeAssembler;
import com.mobicare.backendsilviobassijunior.api.v1.model.SectorModel;
import com.mobicare.backendsilviobassijunior.api.v1.model.SectorModelResume;
import com.mobicare.backendsilviobassijunior.api.v1.model.input.SectorInput;
import com.mobicare.backendsilviobassijunior.domain.model.Sector;
import com.mobicare.backendsilviobassijunior.domain.repository.SectorRepository;
import com.mobicare.backendsilviobassijunior.domain.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/v1/sectors")
public class SectorController {
	
	@Autowired
	private SectorService service;

	@Autowired
	private SectorRepository repository;
	
	@Autowired
	private SectorModelResumeAssembler assemblerResume;
	
	@Autowired
	private SectorModelAssembler assembler;
	
	@Autowired
	private SectorDisassembler disassembler;

	@GetMapping("/collaborators")
	public ResponseEntity<List<SectorModel>> findAll(ServletWebRequest request){
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

		String eTag = "0";

		OffsetDateTime lastUpdate = repository.getLastUpdateDate();

		if(lastUpdate != null){
			eTag = String.valueOf(lastUpdate.toEpochSecond());
		}

		if(request.checkNotModified(eTag)){
			return null;
		}

		List<Sector> sectors = repository.findAll();

		List<SectorModel> sectorModels =  assembler.toCollectionResource(sectors);

		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
				.eTag(eTag)
				.body(sectorModels);

	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public SectorModelResume store(@RequestBody @Valid SectorInput sectorInput){
		Sector sector = disassembler.toDomainModel(sectorInput);
		return assemblerResume.toResource(service.store(sector));
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{sectorId}")
	public SectorModelResume update(@RequestBody @Valid SectorInput sectorInput, @PathVariable Long sectorId){
		
		Sector currentSector = service.findOrFail(sectorId);
	
		disassembler.copyToDomainModel(sectorInput, currentSector);
		
		currentSector = service.store(currentSector);
		
		return assemblerResume.toResource(currentSector);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{sectorId}")
	public SectorModel findById(@PathVariable Long sectorId){
		
		Sector sector = service.findOrFail(sectorId);
		return assembler.toResource(sector);

	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{sectorId}")
	public void deleteById(@PathVariable Long sectorId){
		service.deleteById(sectorId);
	}

}
