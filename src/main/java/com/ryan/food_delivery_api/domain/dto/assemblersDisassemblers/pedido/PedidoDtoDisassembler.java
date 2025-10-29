package com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.pedido;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Pedido;
import com.ryan.food_delivery_api.domain.dto.pedido.PedidoInputDto;
@Component
public class PedidoDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Pedido toDomainObject(PedidoInputDto inputDto){
        return modelMapper.map(inputDto, Pedido.class);
    }

    public void copyToDomainObject(PedidoInputDto inputDto, Pedido entit){
        modelMapper.map(inputDto, entit);
    }

}
