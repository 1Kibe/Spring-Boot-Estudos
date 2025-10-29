package com.ryan.food_delivery_api.domain.dto.endereco;

import com.ryan.food_delivery_api.domain.dto.cidade.CidadeResunDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDto {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeResunDto cidade;

}
