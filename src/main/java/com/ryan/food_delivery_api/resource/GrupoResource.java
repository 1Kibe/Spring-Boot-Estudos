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

import com.ryan.food_delivery_api.domain.Grupo;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.grupo.GrupoDtoAssembler;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.grupo.GrupoDtoDisassembler;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.permissao.PermissaoDtoAssembler;
import com.ryan.food_delivery_api.domain.dto.grupo.GrupoDto;
import com.ryan.food_delivery_api.domain.dto.grupo.GrupoInputDto;
import com.ryan.food_delivery_api.domain.dto.permissao.PermissaoDto;
import com.ryan.food_delivery_api.exception.NegocioException;
import com.ryan.food_delivery_api.exception.grupo.GrupoNaoEncontradoException;
import com.ryan.food_delivery_api.service.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoResource {

    @Autowired
    private GrupoService service;

    @Autowired
    private GrupoDtoAssembler assembler;

    @Autowired
    private GrupoDtoDisassembler disassembler;

    // ===

    @Autowired
    private PermissaoDtoAssembler permissaoDtoAssembler;

    // ===

    @GetMapping()
    public List<GrupoDto> listar() {
        return assembler.toCollectionModel(service.listar());
    }

    @GetMapping("/{id}")
    public GrupoDto findById(@PathVariable Long id) {
        Grupo obj = service.buscarOuFalhar(id);
        return assembler.toModel(obj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoDto salvar(@RequestBody GrupoInputDto input) {
        try {
            Grupo entity = disassembler.toDomainObject(input);

            return assembler.toModel(entity);
        } catch (GrupoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public GrupoDto atualizar(@PathVariable Long id, @RequestBody GrupoInputDto input) {
        try {

            Grupo entidadeAtual = service.buscarOuFalhar(id);

            disassembler.copyToDomainObject(input, entidadeAtual);

            return assembler.toModel(service.salvar(entidadeAtual));
        } catch (GrupoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    // ================================

    // get grupos/id/permissoes
    @GetMapping("/{id}/permissoes")
    public List<PermissaoDto> listarPermissoes(@PathVariable Long id) {
        Grupo entity = service.buscarOuFalhar(id);

        return permissaoDtoAssembler.toCollectionModel(entity.getPermissoes());
    }

    // delete grupos/id/permissoes/idP
    @PutMapping("/{id}/permissoes/{idP}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adcionarPermissoes(@PathVariable Long id, @PathVariable Long idP) {
        service.adcionarPermissoes(id, idP);
    }

    // put grupos/id/permissoes/idP
    @DeleteMapping("/{id}/permissoes/{idP}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerPermissoes(@PathVariable Long id, @PathVariable Long idP) {
        service.removerPermissoes(id, idP);
    }
    // ================================

}
