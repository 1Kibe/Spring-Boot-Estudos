package com.ryan.food_delivery_api.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ryan.food_delivery_api.domain.Cozinha;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.cozinha.CozinhaDtoAssembler;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.cozinha.CozinhaDtoDisassembler;
import com.ryan.food_delivery_api.domain.dto.cozinha.CozinhaDto;
import com.ryan.food_delivery_api.domain.dto.cozinha.CozinhaInputDto;
import com.ryan.food_delivery_api.service.CozinhaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaResource {

    @Autowired
    private CozinhaService service;

    @Autowired
    private CozinhaDtoAssembler cozinhaDtoAssembler;

    @Autowired
    private CozinhaDtoDisassembler cozinhaDtoDisassembler;

    public CozinhaResource(CozinhaService service) {
        this.service = service;
    }

    @GetMapping()
    public List<CozinhaDto> listar() {
        return cozinhaDtoAssembler.toCollectionModel(service.listar());
    }

    @GetMapping("/{id}")
    public CozinhaDto findById(@PathVariable Long id) {
        return cozinhaDtoAssembler.toModel(service.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaDto salvar(@RequestBody CozinhaInputDto inputDto) {
        Cozinha cozinha = cozinhaDtoDisassembler.toDomainObject(inputDto);
        return cozinhaDtoAssembler.toModel(service.salvar(cozinha));
    }

    @PutMapping("/{id}")
    public CozinhaDto atualizar(@PathVariable Long id, @RequestBody CozinhaInputDto entity) {
        Cozinha atual = service.buscarOuFalhar(id);

        cozinhaDtoDisassembler.copyToDomainObject(entity, atual);

        return cozinhaDtoAssembler.toModel(service.salvar(atual)) ;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

}
