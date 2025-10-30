package com.ryan.food_delivery_api.domain.dto.permissao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoDto {

    private Long id;
    private String nome;
    private String descricao;
}
