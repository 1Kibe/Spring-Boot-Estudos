package com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.restaurante;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Restaurante;
import com.ryan.food_delivery_api.domain.dto.restaurante.RestauranteDto;

@Component
public class RestauranteDtoAssembler {
    /**
     * Responsável por converter entidades de domínio {@link Restaurante}
     * em representações de saída {@link RestauranteDto}.
     * 
     * Essa classe centraliza a lógica de conversão, utilizando o
     * {@link ModelMapper}
     * para automatizar o mapeamento de propriedades entre as classes.
     * 
     * Dessa forma, a API nunca expõe diretamente as entidades JPA,
     * mantendo o isolamento entre o domínio e a camada de apresentação (DTOs).
     *
     * Métodos principais:
     * - {@code toModel}: converte uma única entidade em DTO.
     * - {@code toCollectionModel}: converte listas de entidades em listas de DTOs.
     */

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteDto toModel(Restaurante entity) {
        return modelMapper.map(entity, RestauranteDto.class);
    }

    public List<RestauranteDto> toCollectionModel(List<Restaurante> entitys) {
        return entitys.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }

}
