package com.mobicare.backendsilviobassijunior.api.v1.assembler;


import com.mobicare.backendsilviobassijunior.api.v1.model.input.CollaboratorInput;
import com.mobicare.backendsilviobassijunior.domain.model.Collaborator;
import com.mobicare.backendsilviobassijunior.domain.model.Sector;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CollaboratorDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Collaborator toDomainModel(CollaboratorInput collaboratorInput) {
        return modelMapper.map(collaboratorInput, Collaborator.class);
    }

    public void copyToDomainModel(CollaboratorInput collaboratorInput, Collaborator collaborator) {
    	
    	//Resolved [org.springframework.orm.jpa.JpaSystemException: identifier of an instance of 
    	//com.mobicare.backendsilviobassijunior.domain.model.Sector was altered from 1 to 2
    	collaborator.setSector(new Sector());
    	
        modelMapper.map(collaboratorInput, collaborator);
    }
}
