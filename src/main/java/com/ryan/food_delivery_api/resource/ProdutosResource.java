package com.ryan.food_delivery_api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryan.food_delivery_api.domain.Produto;
import com.ryan.food_delivery_api.domain.Restaurante;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.produto.ProdutoDtoAssembler;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.produto.ProdutoDtoDisassembler;
import com.ryan.food_delivery_api.domain.dto.produto.ProdutoDto;
import com.ryan.food_delivery_api.domain.dto.produto.ProdutoInputDto;
import com.ryan.food_delivery_api.repository.ProdutoRepository;
import com.ryan.food_delivery_api.service.ProdutoService;
import com.ryan.food_delivery_api.service.RestauranteService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("restaurantes/{idR}/produtos")
public class ProdutosResource {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoDtoAssembler assembler;

    @Autowired
    private ProdutoDtoDisassembler disassembler;

    @Autowired
    private ProdutoRepository repository;

    // ===

    @Autowired
    private RestauranteService restauranteService;

    // ===

    // Lista todos os produtos de um restaurante específico.
    // Busca o restaurante pelo ID, recupera os produtos associados e converte para
    // DTOs.
    @GetMapping
    public List<ProdutoDto> listar(@PathVariable Long idR) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(idR);

        List<Produto> todosProdutos = repository.findByRestaurante(restaurante);

        return assembler.toCollectionModel(todosProdutos);

    }

    // Busca um produto específico de um restaurante pelo ID do restaurante e do
    // produto.
    // Lança exceção caso o produto não pertença ao restaurante ou não exista.
    @GetMapping("/{idP}")
    public ProdutoDto buscar(@PathVariable Long idR, @PathVariable Long idP) {
        Produto entity = service.buscarOuFalhar(idR, idP);
        return assembler.toModel(entity);
    }

    // Cadastra um novo produto para o restaurante informado.
    // Converte o DTO de entrada para a entidade, associa o restaurante e salva no
    // banco.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDto salvar(@PathVariable Long idR,
            @RequestBody @Valid ProdutoInputDto input) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(idR);
        Produto produto = disassembler.toDomainObject(input);
        produto.setRestaurante(restaurante);

        produto = service.salvar(produto);

        return assembler.toModel(produto);
    }

    // Atualiza um produto existente de um restaurante.
    // Busca o produto atual, copia os novos dados do DTO e retorna o objeto
    // atualizado.
    @PutMapping("/{idP}")
    public ProdutoDto atualizar(@PathVariable Long idR, @PathVariable Long idP, @RequestBody ProdutoInputDto input) {
        Produto produtoAtual = service.buscarOuFalhar(idR, idP);

        disassembler.copyToDomainObject(input, produtoAtual);

        produtoAtual = service.salvar(produtoAtual);

        return assembler.toModel(produtoAtual);
    }

}
