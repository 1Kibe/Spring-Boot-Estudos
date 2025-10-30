package com.ryan.food_delivery_api.domain.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioComSenhaInputDto extends UsuarioInputDto {
    
    @NotBlank
    private String senha;
}
