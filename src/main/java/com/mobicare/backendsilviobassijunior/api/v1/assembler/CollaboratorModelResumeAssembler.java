package com.mobicare.backendsilviobassijunior.api.v1.assembler;

import com.mobicare.backendsilviobassijunior.api.v1.model.CollaboratorModelResume;
import com.mobicare.backendsilviobassijunior.domain.model.Collaborator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class  CollaboratorModelResumeAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CollaboratorModelResume toResource(Collaborator collaborator){
        return modelMapper.map(collaborator, CollaboratorModelResume.class);
    }

    public List<CollaboratorModelResume> toCollectionResource(List<Collaborator> collaborators){
        return collaborators.stream().map(this::toResource)
                .collect(Collectors.toList());
    }
}
