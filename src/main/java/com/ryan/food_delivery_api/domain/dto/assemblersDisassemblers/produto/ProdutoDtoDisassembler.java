package com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.produto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Produto;
import com.ryan.food_delivery_api.domain.dto.produto.ProdutoInputDto;
@Component
public class ProdutoDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Produto toDomainObject(ProdutoInputDto inputDto){
        return modelMapper.map(inputDto, Produto.class);
    }

    public void copyToDomainObject(ProdutoInputDto inputDto, Produto entit){
        modelMapper.map(inputDto, entit);
    }

}
