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

import com.ryan.food_delivery_api.domain.Cidade;
import com.ryan.food_delivery_api.domain.dto.assembler.cidade.CidadeDtoAssembler;
import com.ryan.food_delivery_api.domain.dto.assembler.cidade.CidadeDtoDisassembler;
import com.ryan.food_delivery_api.domain.dto.cidade.CidadeDto;
import com.ryan.food_delivery_api.domain.dto.cidade.CidadeInputDto;
import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;
import com.ryan.food_delivery_api.exception.NegocioException;
import com.ryan.food_delivery_api.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

    @Autowired
    private CidadeService service;

    @Autowired
    private CidadeDtoAssembler cidadeDtoAssembler;

    @Autowired
    private CidadeDtoDisassembler cidadeDtoDisassembler;

    public CidadeResource(CidadeService service) {
        this.service = service;
    }


    @GetMapping()
    public List<CidadeDto> listar() {
        return cidadeDtoAssembler.toCollectionModel(service.listar());
    }

    @GetMapping("/{id}")
    public CidadeDto findById(@PathVariable Long id){
        return cidadeDtoAssembler.toModel(service.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDto salvar(@RequestBody CidadeInputDto obj) {
        try{
            Cidade entity = cidadeDtoDisassembler.toDomainObject(obj);
            return cidadeDtoAssembler.toModel(service.salvar(entity));
        } catch (EntidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public CidadeDto atualizar(@PathVariable Long id, @RequestBody CidadeInputDto entity) {
            Cidade entidadeAtual = service.buscarOuFalhar(id);

            cidadeDtoDisassembler.copyToDomainObject(entity, entidadeAtual);

            try{
                return cidadeDtoAssembler.toModel(service.salvar(entidadeAtual));
            }catch(EntidadeNaoEncontradaException e){
                throw new NegocioException(e.getMessage(),e);
            }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    /*
     Buscar cidade inexistente (GET /cidades/{id})
     🚫 Lança EntidadeNaoEncontradaException
     Resposta 404 NOT FOUND

     Essa mensagem vem do seu método buscarOuFalhar()
     throw new EntidadeNaoEncontradaException(String.format("Cidade de código %d não encontrada", id));




     Cadastrar cidade com estado inválido (POST /cidades)
     🚫 O estadoService.buscarOuFalhar(9999) lança EntidadeNaoEncontradaException,
     que o controller captura e relança como NegocioException.
     Resposta 400 BAD REQUEST





     Atualizar cidade inexistente (PUT /cidades/{id})
     🚫 O service.buscarOuFalhar(123) lança EntidadeNaoEncontradaException
     Resposta 404 NOT FOUND





     Excluir cidade inexistente (DELETE /cidades/{id})
     🚫 repository.deleteById(123) lança EmptyResultDataAccessException 
     ➡️ Convertida em EntidadeNaoEncontradaException.
     Resposta 404 NOT FOUND





     Excluir cidade em uso (DELETE /cidades/{id})
     🚫 DataIntegrityViolationException → EntidadeEmUsoException.
     Resposta 409 CONFLICT
    */

}
