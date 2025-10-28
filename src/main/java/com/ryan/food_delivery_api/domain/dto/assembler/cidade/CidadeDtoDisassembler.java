package com.ryan.food_delivery_api.domain.dto.assembler.cidade;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.ryan.food_delivery_api.domain.Estado;
import com.ryan.food_delivery_api.domain.Cidade;
import com.ryan.food_delivery_api.domain.dto.cidade.CidadeInputDto;

public class CidadeDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainObject(CidadeInputDto inputDto){
        return modelMapper.map(inputDto, Cidade.class);
    }

    public void copyToDomainObject(CidadeInputDto inputDto, Cidade entit){
        //Trata essa execption org.hibernate.Hibernateexception: identifier os an instance of 
        //com.ryan.food_delivery_api.domain.Estado was altered from 1 to 2
        entit.setEstado(new Estado());
        modelMapper.map(inputDto, entit);
    }

}
