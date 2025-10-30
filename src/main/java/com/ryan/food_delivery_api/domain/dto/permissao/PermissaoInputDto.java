package com.ryan.food_delivery_api.domain.dto.permissao;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoInputDto {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
}
