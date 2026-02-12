package com.ryan.food_delivery_api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ryan.food_delivery_api.domain.Pedido;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.pedido.PedidoDtoAssembler;
import com.ryan.food_delivery_api.domain.dto.pedido.PedidoDto;
import com.ryan.food_delivery_api.domain.dto.pedido.PedidoInputDto;
import com.ryan.food_delivery_api.domain.dto.pedido.PedidoResunDto;
import com.ryan.food_delivery_api.exception.EntidadeEmUsoException;
import com.ryan.food_delivery_api.exception.NegocioException;
import com.ryan.food_delivery_api.repository.PedidoRepository;
import com.ryan.food_delivery_api.service.PedidoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


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
    public PedidoDto buscar(@PathVariable Long id) {
        Pedido pedido = service.buscarOuFalhar(id);
        return assembler.toModel(pedido);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void adcionarPedido(@RequestBody PedidoInputDto input) {
        try {
            service.insert(input);
        } catch (EntidadeEmUsoException e) {
            throw new NegocioException(e.getMessage());
        }


    }


}
