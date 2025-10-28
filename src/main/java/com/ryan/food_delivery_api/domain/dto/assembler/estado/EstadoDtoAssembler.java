package com.ryan.food_delivery_api.domain.dto.assembler.estado;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Estado;
import com.ryan.food_delivery_api.domain.dto.estado.EstadoDto;

@Component
public class EstadoDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public EstadoDto toModel(Estado entity) {
        return modelMapper.map(entity, EstadoDto.class);
    }

    public List<EstadoDto> toCollectionModel(List<Estado> entityS) {
        return entityS.stream()
                .map(estado -> toModel(estado))
                .collect(Collectors.toList());
    }

}
