package com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.grupo;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Grupo;
import com.ryan.food_delivery_api.domain.dto.grupo.GrupoDto;

@Component
public class GrupoDtoAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public GrupoDto toModel(Grupo entity) {
        return modelMapper.map(entity, GrupoDto.class);
    }

    public List<GrupoDto> toCollectionModel(List<Grupo> entityS) {
        return entityS.stream()
                .map(pedido -> toModel(pedido))
                .collect(Collectors.toList());
    }
}
