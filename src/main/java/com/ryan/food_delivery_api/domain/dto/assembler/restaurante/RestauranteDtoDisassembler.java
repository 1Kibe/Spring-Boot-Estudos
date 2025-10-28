package com.ryan.food_delivery_api.domain.dto.assembler.restaurante;

import com.ryan.food_delivery_api.domain.Cozinha;
import com.ryan.food_delivery_api.domain.Restaurante;
import com.ryan.food_delivery_api.domain.dto.restaurante.RestauranteInputDto;

public class RestauranteDtoDisassembler {

    public Restaurante toDomainObject(RestauranteInputDto inputDto){
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(inputDto.getNome());
        restaurante.setTaxaFrete(inputDto.getTaxaFrete());

        Cozinha cozinha = new Cozinha();
        cozinha.setId(inputDto.getCozinha().getId());

        restaurante.setCozinha(cozinha);

        return restaurante;

    }

}
