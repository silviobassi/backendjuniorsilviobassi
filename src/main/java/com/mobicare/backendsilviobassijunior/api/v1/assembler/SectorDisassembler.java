package com.mobicare.backendsilviobassijunior.api.v1.assembler;


import com.mobicare.backendsilviobassijunior.api.v1.model.input.SectorInput;
import com.mobicare.backendsilviobassijunior.domain.model.Sector;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SectorDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Sector toDomainModel(SectorInput sectorInput) {
        return modelMapper.map(sectorInput, Sector.class);
    }

    public void copyToDomainModel(SectorInput sectorInput, Sector sector) {
        modelMapper.map(sectorInput, sector);
    }
}
