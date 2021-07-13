package com.mobicare.backendsilviobassijunior.api.v1.assembler;

import com.mobicare.backendsilviobassijunior.api.v1.model.CollaboratorModel;
import com.mobicare.backendsilviobassijunior.domain.model.Collaborator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class  CollaboratorModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CollaboratorModel toResource(Collaborator collaborator){
        return modelMapper.map(collaborator, CollaboratorModel.class);
    }

    public List<CollaboratorModel> toCollectionResource(List<Collaborator> collaborators){
        return collaborators.stream().map(this::toResource)
                .collect(Collectors.toList());
    }
}
