package com.ryan.food_delivery_api.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryan.food_delivery_api.domain.Pedido;
import com.ryan.food_delivery_api.domain.Usuario;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.pedido.PedidoDtoAssembler;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.pedido.PedidoDtoDisassembler;
import com.ryan.food_delivery_api.domain.dto.pedido.PedidoDto;
import com.ryan.food_delivery_api.domain.dto.pedido.PedidoInputDto;
import com.ryan.food_delivery_api.domain.dto.pedido.PedidoResunDto;
import com.ryan.food_delivery_api.exception.EntidadeEmUsoException;
import com.ryan.food_delivery_api.exception.NegocioException;
import com.ryan.food_delivery_api.repository.PedidoRepository;
import com.ryan.food_delivery_api.service.PedidoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private PedidoDtoAssembler assembler;

    @Autowired
    private PedidoDtoDisassembler disassembler;

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

    @PostMapping()
    public PedidoDto adcionarPedido(@RequestBody PedidoInputDto input) {
        try{
            Pedido pedido = disassembler.toDomainObject(input);

            pedido.setCliente(new Usuario());
            pedido.getCliente().setId(1L);

            return assembler.toModel(pedido);
        }catch(EntidadeEmUsoException e ){
            throw new NegocioException(e.getMessage());
        }

        
    }
    
    
}
