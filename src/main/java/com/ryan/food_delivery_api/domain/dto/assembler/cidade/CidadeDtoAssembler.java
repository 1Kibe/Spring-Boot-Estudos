package com.ryan.food_delivery_api.domain.dto.assembler.cidade;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Cidade;
import com.ryan.food_delivery_api.domain.dto.cidade.CidadeDto;

@Component
public class CidadeDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeDto toModel(Cidade entity) {
        return modelMapper.map(entity, CidadeDto.class);
    }

    public List<CidadeDto> toCollectionModel(List<Cidade> entitys) {
        return entitys.stream()
                .map(cidade -> toModel(cidade))
                .collect(Collectors.toList());
    }

}
