package com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.restaurante;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Restaurante;
import com.ryan.food_delivery_api.domain.dto.restaurante.RestauranteDto;

@Component
public class RestauranteDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteDto toModel(Restaurante entity) {
        return modelMapper.map(entity, RestauranteDto.class);
    }

    public List<RestauranteDto> toCollectionModel(List<Restaurante> entitys) {
        return entitys.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }

}
