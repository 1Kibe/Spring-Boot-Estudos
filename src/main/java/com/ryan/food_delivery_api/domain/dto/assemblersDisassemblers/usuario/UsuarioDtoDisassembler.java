package com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.usuario;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.food_delivery_api.domain.Usuario;
import com.ryan.food_delivery_api.domain.dto.usuario.UsuarioInputDto;

@Component
public class UsuarioDtoDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioInputDto inputDto){
        return modelMapper.map(inputDto, Usuario.class);
    }

    public void copyToDomainObject(UsuarioInputDto inputDto, Usuario entit){
        modelMapper.map(inputDto, entit);
    }
}
