package com.mobicare.backendsilviobassijunior.domain.service;

import com.mobicare.backendsilviobassijunior.api.client.BlackListClient;
import com.mobicare.backendsilviobassijunior.domain.exception.BusinessException;
import com.mobicare.backendsilviobassijunior.domain.exception.CollaboratorNotFoundException;
import com.mobicare.backendsilviobassijunior.domain.model.Collaborator;
import com.mobicare.backendsilviobassijunior.domain.model.Sector;
import com.mobicare.backendsilviobassijunior.domain.repository.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CollaboratorService {

	private static final String CPF_EXISTS = "O cpf: %s informado já existe!";
	public static final String EMAIL_EXISTS = "O email: %s informado, já existe!";

	private static final Integer YOUNG_AGE = 18;
	private static final Integer SENIOR_AGE = 65;
	private static final float PERCENTAGE_ALLOWED = 0.2f;
	
	private static final String NO_VACANCIE_SENIORS = "No momento, não há vagas para colaboradores maiores de " + SENIOR_AGE +" anos.";
	private static final String NO_VACANCIE_YOUNG = "No momento, não há vagas para colaboradores menores de " + YOUNG_AGE +" anos.";
	private static final String BLACKLISTED_COLLABORATOR = "O colaborador com o cpf: %s não pode ser inserido no setor!";


	@Autowired
	private CollaboratorRepository repository;
	
	@Autowired
	private SectorService sectorService;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private EntityManager manager;
	
	@Transactional
	public Collaborator store(Collaborator collaborator) {
		manager.detach(collaborator);

		Sector sector = sectorService.findOrFail(collaborator.getSector().getId());
		
		Optional<Collaborator> existingCollaboratorCpf = repository.findByCpf(collaborator.getCpf());
		Optional<Collaborator> existingCollaboratorDateOfBirth = repository.findByDateOfBirth(collaborator.getDateOfBirth());
		Optional<Collaborator> existingCollaboratorEmail = repository.findByEmail(collaborator.getEmail());

		if(isNotEqualsModel(collaborator, existingCollaboratorCpf)) {
			throw new BusinessException(String.format(CPF_EXISTS, collaborator.getCpf()));
		}

		if(isNotEqualsModel(collaborator, existingCollaboratorEmail)){
			throw new BusinessException(String.format(EMAIL_EXISTS, collaborator.getEmail()));
		}

		if(isNotEqualsModel(collaborator, existingCollaboratorDateOfBirth)){
			isNotSeniorsAllowed(collaborator);
		}

		isNotYoungAllowed(collaborator, sector);

		isBlackList(collaborator);

		collaborator.setSector(sector);

		return repository.save(collaborator);
	}

	@Transactional
	public void deleteById(Long collaboratorId) {
		
		try {
			repository.deleteById(collaboratorId);
			repository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new CollaboratorNotFoundException(collaboratorId);
			
		}
	}
	
	public Collaborator findOrFail(Long collaboratorId) {
		
		Collaborator collaborator = repository.findById(collaboratorId)
				.orElseThrow(() -> new CollaboratorNotFoundException(collaboratorId));
		return collaborator;
	}

	private void isBlackList(Collaborator collaborator) {
		BlackListClient blackList = new BlackListClient(restTemplate);
		
		if(!blackList.findByCpf(collaborator.getCpf()).isEmpty()) {
			throw new BusinessException(
					String.format(BLACKLISTED_COLLABORATOR, collaborator.getCpf()));
		}
		
	}

	private void isNotYoungAllowed(Collaborator collaborator, Sector sector) {
		collaborator.calculateAge();

		var countCollaboratorsBySector = repository.countCollaboratorsBySector(sector);
		var countCollaboratorsAllowedYoung = repository.countCollaboratorByAgeYoung(YOUNG_AGE);
		float percentageYoung = getPercentage(countCollaboratorsBySector, countCollaboratorsAllowedYoung);

		if(collaborator.getAge() < YOUNG_AGE && percentageYoung > PERCENTAGE_ALLOWED)
			throw new BusinessException(String.format(String.format(NO_VACANCIE_YOUNG), collaborator.getAge()));
	}

	private void isNotSeniorsAllowed(Collaborator collaborator) {
		collaborator.calculateAge();

		var countCollaborators = repository.countCollaborators();
		var countCollaboratorsAllowedSeniors = repository.countCollaboratorByAgeSenior(SENIOR_AGE);
		float percentageSenior = getPercentage(countCollaborators, countCollaboratorsAllowedSeniors);

		if(collaborator.getAge() > SENIOR_AGE && percentageSenior > PERCENTAGE_ALLOWED)
			throw new BusinessException(String.format(String.format(NO_VACANCIE_SENIORS), collaborator.getAge()));
	}

	private float getPercentage(Long countCollaboratorsBySector, Long countCollaboratorsAllowedYoung) {
		return (Float.valueOf(countCollaboratorsAllowedYoung) + 1) / (Float.valueOf(countCollaboratorsBySector) + 1);
	}

	private boolean isNotEqualsModel(Collaborator currentCollaborator, Optional<Collaborator> existingCollaborator) {
		return existingCollaborator.isPresent() && !existingCollaborator.get().equals(currentCollaborator);
	}

}

