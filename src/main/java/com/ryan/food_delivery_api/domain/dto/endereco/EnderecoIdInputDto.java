package com.ryan.food_delivery_api.domain.dto.endereco;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoIdInputDto {
    @NotNull
    private Long id;
}
