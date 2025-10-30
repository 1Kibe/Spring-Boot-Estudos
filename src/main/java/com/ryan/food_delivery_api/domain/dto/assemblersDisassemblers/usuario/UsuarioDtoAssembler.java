package com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.usuario;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Usuario;
import com.ryan.food_delivery_api.domain.dto.usuario.UsuarioDto;

@Component
public class UsuarioDtoAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDto toModel(Usuario entity) {
        return modelMapper.map(entity, UsuarioDto.class);
    }

    public List<UsuarioDto> toCollectionModel(List<Usuario> entitys) {
        return entitys.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }
}
