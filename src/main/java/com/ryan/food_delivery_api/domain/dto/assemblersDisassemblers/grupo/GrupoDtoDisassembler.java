package com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.grupo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Grupo;
import com.ryan.food_delivery_api.domain.dto.grupo.GrupoInputDto;

@Component
public class GrupoDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Grupo toDomainObject(GrupoInputDto inputDto){
        return modelMapper.map(inputDto, Grupo.class);
    }

    public void copyToDomainObject(GrupoInputDto inputDto, Grupo entity){
        modelMapper.map(inputDto, entity);
    }

}
