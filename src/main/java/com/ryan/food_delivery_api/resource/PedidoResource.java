package com.ryan.food_delivery_api.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryan.food_delivery_api.domain.Pedido;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.pedido.PedidoDtoAssembler;
import com.ryan.food_delivery_api.domain.dto.pedido.PedidoDto;
import com.ryan.food_delivery_api.domain.dto.pedido.PedidoResunDto;
import com.ryan.food_delivery_api.repository.PedidoRepository;
import com.ryan.food_delivery_api.service.PedidoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private PedidoDtoAssembler assembler;

    @GetMapping
    public List<PedidoResunDto> listar() {
        List<Pedido> todosPedidos = repository.findAll();

        return assembler.toCollectionModelResun(todosPedidos);
    }

    @GetMapping("/{id}")
    public PedidoDto buscar(@PathVariable Long id){
        Pedido pedido = service.buscarOuFalhar(id);
        return assembler.toModel(pedido);
    }
    
}
