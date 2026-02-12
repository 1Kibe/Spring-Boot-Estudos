package com.ryan.food_delivery_api.domain.dto.endereco;

import com.fasterxml.jackson.annotation.JsonView;
import com.ryan.food_delivery_api.domain.dto.cidade.CidadeResunDto;

import com.ryan.food_delivery_api.domain.dto.views.RestauranteView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDto {

    @JsonView(RestauranteView.Resumo.class)
    private String cep;

    @JsonView(RestauranteView.Resumo.class)
    private String logradouro;
    @JsonView(RestauranteView.Resumo.class)
    private String numero;
    private String complemento;
    @JsonView(RestauranteView.Resumo.class)
    private String bairro;
    @JsonView(RestauranteView.Resumo.class)
    private CidadeResunDto cidade;

}
