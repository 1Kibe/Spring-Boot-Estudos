package com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.formaPagamento;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.FormaPagamento;
import com.ryan.food_delivery_api.domain.dto.formaPagamento.FormaPagamentoDto;

@Component
public class FormaPagamentoDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoDto toModel(FormaPagamento entity) {
        return modelMapper.map(entity, FormaPagamentoDto.class);
    }

    public List<FormaPagamentoDto> toCollectionModel(List<FormaPagamento> entityS) {
        return entityS.stream()
                .map(formaPagamento -> toModel(formaPagamento))
                .collect(Collectors.toList());
    }

}
