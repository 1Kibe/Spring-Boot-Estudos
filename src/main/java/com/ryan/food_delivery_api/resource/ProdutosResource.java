package com.ryan.food_delivery_api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryan.food_delivery_api.domain.Produto;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.produto.ProdutoDtoAssembler;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.produto.ProdutoDtoDisassembler;
import com.ryan.food_delivery_api.domain.dto.produto.ProdutoDto;
import com.ryan.food_delivery_api.domain.dto.produto.ProdutoInputDto;
import com.ryan.food_delivery_api.exception.NegocioException;
import com.ryan.food_delivery_api.exception.produto.ProdutoNaoEncontradoException;
import com.ryan.food_delivery_api.service.ProdutoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/produtos")
public class ProdutosResource {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoDtoAssembler assembler;

    @Autowired
    private ProdutoDtoDisassembler disassembler;

    @GetMapping()
    public List<ProdutoDto> listar() {
        return assembler.toCollectionModel(service.listar()) ;
    }

    @GetMapping("/{id}")
    public ProdutoDto buscar(@PathVariable Long Id) {
        Produto entity = service.buscarOuFalhar(Id);
        return assembler.toModel(entity);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProdutoDto salvar(@RequestBody ProdutoInputDto input) {
        try{
            Produto entity = disassembler.toDomainObject(input);
            return assembler.toModel(entity);
        }catch(ProdutoNaoEncontradoException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ProdutoDto atualizar(@PathVariable Long id, @RequestBody ProdutoInputDto input) {
        try{
            Produto entity = service.buscarOuFalhar(id);
            disassembler.copyToDomainObject(input, entity);

            return assembler.toModel(service.salvar(entity));
        }catch(ProdutoNaoEncontradoException e){
            throw new NegocioException(e.getMessage());
        }
    }
    
    
    
}
