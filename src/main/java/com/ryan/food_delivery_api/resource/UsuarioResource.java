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

import com.ryan.food_delivery_api.domain.Usuario;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.grupo.GrupoDtoAssembler;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.usuario.UsuarioDtoAssembler;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.usuario.UsuarioDtoDisassembler;
import com.ryan.food_delivery_api.domain.dto.grupo.GrupoDto;
import com.ryan.food_delivery_api.domain.dto.usuario.SenhaInput;
import com.ryan.food_delivery_api.domain.dto.usuario.UsuarioComSenhaInputDto;
import com.ryan.food_delivery_api.domain.dto.usuario.UsuarioDto;
import com.ryan.food_delivery_api.domain.dto.usuario.UsuarioInputDto;
import com.ryan.food_delivery_api.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioDtoAssembler assembler;

    @Autowired
    private UsuarioDtoDisassembler disassembler;

    // ===

    @Autowired
    private GrupoDtoAssembler grupoDtoAssembler;

    // ===

    public UsuarioResource(UsuarioService service) {
        this.service = service;
    }

    @GetMapping()
    public List<UsuarioDto> listar() {
        return assembler.toCollectionModel(service.listar());
    }

    @GetMapping("/{id}")
    public UsuarioDto findById(@PathVariable Long id) {
        return assembler.toModel(service.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto salvar(@RequestBody UsuarioComSenhaInputDto obj) {
        Usuario entity = disassembler.toDomainObject(obj);
        return assembler.toModel(entity);
    }

    @PutMapping("/{id}")
    public UsuarioDto atualizar(@PathVariable Long id, @RequestBody UsuarioInputDto entity) {
        Usuario entidadeAtual = service.buscarOuFalhar(id);

        disassembler.copyToDomainObject(entity, entidadeAtual);

        return assembler.toModel(entidadeAtual);

    }

    @PutMapping("/{id}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long id, @RequestBody @Valid SenhaInput input) {
        service.alterarSenha(id, input.getSenhaAtual(), input.getNovaSenha());
    }

    // ===========================================

    // get usuarios/id/grupos
    @GetMapping("j")
    public List<GrupoDto> listarGrupos(@PathVariable Long id) {
        Usuario usuario = service.buscarOuFalhar(id);
        return grupoDtoAssembler.toCollectionModel(usuario.getGrupos());
    }

    // put usuarios/id/grupos/idG
    @PutMapping("/{id}/grupos/{idG}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void vincularGrupo(@PathVariable Long id, @PathVariable Long idG) {
        service.vincularGrupo(id, idG);
    }

    // delete usuarios/id/grupos/idG
    @DeleteMapping("/{id}/grupos/{idG}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void desvincularGrupo(@PathVariable Long id, @PathVariable Long idG) {
        service.desvincularGrupo(id, idG);
    }
    // ===========================================

}
