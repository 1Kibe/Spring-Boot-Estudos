package com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.pedido.formaPagamento;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Pedido;
import com.ryan.food_delivery_api.domain.dto.pedido.PedidoDto;

@Component
public class PedidoDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoDto toModel(Pedido entity) {
        return modelMapper.map(entity, PedidoDto.class);
    }

    public List<PedidoDto> toCollectionModel(List<Pedido> entityS) {
        return entityS.stream()
                .map(pedido -> toModel(pedido))
                .collect(Collectors.toList());
    }

}
