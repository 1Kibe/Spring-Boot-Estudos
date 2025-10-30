package com.ryan.food_delivery_api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryan.food_delivery_api.domain.FormaPagamento;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.formaPagamento.FormaPagamentoDtoAssembler;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.formaPagamento.FormaPagamentoDtoDisassembler;
import com.ryan.food_delivery_api.domain.dto.formaPagamento.FormaPagamentoDto;
import com.ryan.food_delivery_api.domain.dto.formaPagamento.FormaPagamentoInputDto;
import com.ryan.food_delivery_api.exception.NegocioException;
import com.ryan.food_delivery_api.exception.formaPagamento.FormaPagamentoNaoEncontradoException;
import com.ryan.food_delivery_api.service.FormaPagamentoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/formas-de-pagamento")
public class FormaPagamentoResource {

    @Autowired
    private FormaPagamentoService service;

    @Autowired
    private FormaPagamentoDtoAssembler formaPagamentoDtoAssembler;

    @Autowired
    private FormaPagamentoDtoDisassembler formaPagamentoDtoDisassembler;

    private FormaPagamentoResource(FormaPagamentoService service) {
        this.service = service;
    }

    @GetMapping()
    public List<FormaPagamentoDto> listar() {
        return formaPagamentoDtoAssembler.toCollectionModel(service.listar());
    }

    @GetMapping("/{id}")
    public FormaPagamentoDto buscar(@PathVariable Long id) {
        FormaPagamento entity = service.buscarOuFalhar(id);
        return formaPagamentoDtoAssembler.toModel(entity);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public FormaPagamentoDto adcionar(@RequestBody FormaPagamentoInputDto input) {
        try {
            FormaPagamento entity = formaPagamentoDtoDisassembler.toDomainObject(input);
            return formaPagamentoDtoAssembler.toModel(entity);
        } catch (FormaPagamentoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public FormaPagamentoDto atualizar(@PathVariable Long id, @RequestBody FormaPagamentoInputDto input) {
        FormaPagamento entityNow = service.buscarOuFalhar(id);

        formaPagamentoDtoDisassembler.toDomainObject(input);

        return formaPagamentoDtoAssembler.toModel(service.salvar(entityNow));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    // ===

    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long id) {
        service.ativar(id);
    }

    @DeleteMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativar(@PathVariable Long id){
        service.desativar(id);
    }
}
