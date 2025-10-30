package com.ryan.food_delivery_api.domain.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputDto {
    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

}
