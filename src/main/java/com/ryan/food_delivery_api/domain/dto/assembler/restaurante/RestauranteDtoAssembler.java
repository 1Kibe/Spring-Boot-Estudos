package com.ryan.food_delivery_api.domain.dto.assembler.restaurante;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Restaurante;
import com.ryan.food_delivery_api.domain.dto.cozinha.CozinhaDto;
import com.ryan.food_delivery_api.domain.dto.restaurante.RestauranteDto;

@Component
public class RestauranteDtoAssembler {

    public RestauranteDto toModel(Restaurante restaurante) {
        CozinhaDto cozinhaDto = new CozinhaDto();
        cozinhaDto.setId(restaurante.getCozinha().getId());
        cozinhaDto.setNome(restaurante.getCozinha().getNome());

        RestauranteDto restauranteDto = new RestauranteDto();
        restaurante.setId(restaurante.getId());
        restauranteDto.setNome(restaurante.getNome());
        restauranteDto.setTaxaFrete(restaurante.getTaxaFrete());
        return restauranteDto;
    }

    public List<RestauranteDto> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }

}
