package com.ryan.food_delivery_api.domain.dto.assembler.restaurante;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.ryan.food_delivery_api.domain.Cozinha;
import com.ryan.food_delivery_api.domain.Restaurante;
import com.ryan.food_delivery_api.domain.dto.restaurante.RestauranteInputDto;

public class RestauranteDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInputDto inputDto){
        return modelMapper.map(inputDto, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInputDto inputDto, Restaurante entit){
        //trata erro de auteracao 
        entit.setCozinha(new Cozinha());
        modelMapper.map(inputDto, entit);
    }

}
