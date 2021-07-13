package com.mobicare.backendsilviobassijunior.api.v1.assembler;

import com.mobicare.backendsilviobassijunior.api.v1.model.SectorModel;
import com.mobicare.backendsilviobassijunior.domain.model.Sector;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class  SectorModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public SectorModel toResource(Sector sector){
        return modelMapper.map(sector, SectorModel.class);
    }

    public List<SectorModel> toCollectionResource(List<Sector> sectors){
        return sectors.stream().map(this::toResource)
                .collect(Collectors.toList());
    }
    
  
}
