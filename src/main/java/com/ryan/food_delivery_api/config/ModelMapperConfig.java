package com.ryan.food_delivery_api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ryan.food_delivery_api.domain.Endereco;
import com.ryan.food_delivery_api.domain.ItemPedido;
import com.ryan.food_delivery_api.domain.dto.endereco.EnderecoDto;
import com.ryan.food_delivery_api.domain.dto.itemPedido.ItemPedidoInputDto;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        // Mapeia ItemPedidoInputDto para ItemPedido
        // Copia todos os campos exceto o 'id', que é gerado pelo banco
        // Evita sobrescrever o ID ao criar um novo ItemPedido
        modelMapper.createTypeMap(ItemPedidoInputDto.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        // Configura o ModelMapper para copiar o nome do estado
        // (src.getCidade().getEstado().getNome())
        // para o campo cidade.estado (String) do DTO, já que o modelo original possui
        // uma hierarquia de objetos.
        var enderecoToEnderecoDtoTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoDto.class);
        enderecoToEnderecoDtoTypeMap.<String>addMapping(
                enderecosrc -> enderecosrc.getCidade().getEstado().getNome(),
                (enderecoDtodest, value) -> enderecoDtodest.getCidade().setEstado(value));

        return modelMapper;
    }
}
