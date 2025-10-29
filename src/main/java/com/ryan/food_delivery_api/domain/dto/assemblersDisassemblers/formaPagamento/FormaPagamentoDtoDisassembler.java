package com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.formaPagamento;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.FormaPagamento;
import com.ryan.food_delivery_api.domain.dto.formaPagamento.FormaPagamentoInputDto;
@Component
public class FormaPagamentoDtoDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamento toDomainObject(FormaPagamentoInputDto inputDto){
        return modelMapper.map(inputDto, FormaPagamento.class);
    }

    public void copyToDomainObject(FormaPagamentoInputDto inputDto, FormaPagamento entit){
        modelMapper.map(inputDto, entit);
    }

}
