package com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.permissao;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Permissao;
import com.ryan.food_delivery_api.domain.dto.permissao.PermissaoDto;

@Component
public class PermissaoDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PermissaoDto toModel(Permissao entity) {
        return modelMapper.map(entity, PermissaoDto.class);
    }

    public List<PermissaoDto> toCollectionModel(List<Permissao> entityS) {
        return entityS.stream()
                .map(pedido -> toModel(pedido))
                .collect(Collectors.toList());
    }

}
