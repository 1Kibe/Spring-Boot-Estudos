package com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.cozinha;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Cozinha;
import com.ryan.food_delivery_api.domain.dto.cozinha.CozinhaInputDto;
@Component
public class CozinhaDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cozinha toDomainObject(CozinhaInputDto inputDto){
        return modelMapper.map(inputDto, Cozinha.class);
    }

    public void copyToDomainObject(CozinhaInputDto inputDto, Cozinha entit){
        modelMapper.map(inputDto, entit);
    }
}
