package com.ryan.food_delivery_api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ryan.food_delivery_api.domain.Estado;
import com.ryan.food_delivery_api.domain.dto.assembler.estado.EstadoDtoAssembler;
import com.ryan.food_delivery_api.domain.dto.assembler.estado.EstadoDtoDisassembler;
import com.ryan.food_delivery_api.domain.dto.estado.EstadoDto;
import com.ryan.food_delivery_api.domain.dto.estado.EstadoInputDto;
import com.ryan.food_delivery_api.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

    @Autowired
    private EstadoService service;

    @Autowired
    private EstadoDtoAssembler estadoDtoAssembler;

    @Autowired
    private EstadoDtoDisassembler estadoDtoDisassembler;

    public EstadoResource(EstadoService service) {
        this.service = service;
    }

    @GetMapping()
    public List<EstadoDto> listar() {
        return estadoDtoAssembler.toCollectionModel(service.listar());
    }

    @GetMapping("/{id}")
    public EstadoDto findById(@PathVariable Long id){
        return estadoDtoAssembler.toModel(service.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoDto salvar(@RequestBody EstadoInputDto obj) {
        Estado entity = estadoDtoDisassembler.toDomainObject(obj);
        return estadoDtoAssembler.toModel(entity);
    }

    @PutMapping("/{id}")
    public EstadoDto atualizar(@PathVariable Long id, @RequestBody EstadoInputDto entity) {
            Estado entidadeAtual = service.buscarOuFalhar(id);

            estadoDtoDisassembler.copyToDomainObject(entity, entidadeAtual);

            return estadoDtoAssembler.toModel(entidadeAtual);
            
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
