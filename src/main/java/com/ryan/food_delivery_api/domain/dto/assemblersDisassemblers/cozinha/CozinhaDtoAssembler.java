package com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.cozinha;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Cozinha;
import com.ryan.food_delivery_api.domain.dto.cozinha.CozinhaDto;

@Component
public class CozinhaDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CozinhaDto toModel(Cozinha restaurante) {
        return modelMapper.map(restaurante, CozinhaDto.class);
    }

    public List<CozinhaDto> toCollectionModel(List<Cozinha> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }

}
