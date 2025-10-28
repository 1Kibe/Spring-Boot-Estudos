package com.ryan.food_delivery_api.domain.dto.assembler.estado;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Estado;
import com.ryan.food_delivery_api.domain.dto.estado.EstadoInputDto;
@Component
public class EstadoDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Estado toDomainObject(EstadoInputDto inputDto){
        return modelMapper.map(inputDto, Estado.class);
    }

    public void copyToDomainObject(EstadoInputDto inputDto, Estado entit){
        modelMapper.map(inputDto, entit);
    }

}
