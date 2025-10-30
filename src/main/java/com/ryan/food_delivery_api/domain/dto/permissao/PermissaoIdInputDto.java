package com.ryan.food_delivery_api.domain.dto.permissao;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoIdInputDto {
    @NotNull
    private Long id;
}
