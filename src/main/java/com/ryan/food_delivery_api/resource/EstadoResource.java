package com.ryan.food_delivery_api.resource;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
import com.ryan.food_delivery_api.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

    @Autowired
    private EstadoService service;

    public EstadoResource(EstadoService service) {
        this.service = service;
    }


    @GetMapping()
    public List<Estado> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Estado findById(@PathVariable Long id){
        return service.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado salvar(@RequestBody Estado obj) {
        return service.salvar(obj);
    }

    @PutMapping("/{id}")
    public Estado atualizar(@PathVariable Long id, @RequestBody Estado entity) {
            Estado entidadeAtual = service.buscarOuFalhar(id);

            BeanUtils.copyProperties(entity, entidadeAtual,
            "id");

            return service.salvar(entidadeAtual);
            
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
