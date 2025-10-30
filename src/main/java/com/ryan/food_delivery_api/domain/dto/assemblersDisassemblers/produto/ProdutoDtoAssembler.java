package com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.produto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Produto;
import com.ryan.food_delivery_api.domain.dto.produto.ProdutoDto;

@Component
public class ProdutoDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoDto toModel(Produto entity) {
        return modelMapper.map(entity, ProdutoDto.class);
    }

    public List<ProdutoDto> toCollectionModel(List<Produto> entityS) {
        return entityS.stream()
                .map(pedido -> toModel(pedido))
                .collect(Collectors.toList());
    }

}
