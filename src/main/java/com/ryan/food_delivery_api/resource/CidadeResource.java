package com.ryan.food_delivery_api.resource;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.ryan.food_delivery_api.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

    @Autowired
    private CidadeService service;

    public CidadeResource(CidadeService service) {
        this.service = service;
    }


    @GetMapping()
    public List<Cidade> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Cidade findById(@PathVariable Long id){
        return service.buscarOuFalhar(id);
    }

    @PostMapping
    public ResponseEntity<Cidade> salvar(@RequestBody Cidade obj) {
        Cidade novo = service.salvar(obj);
        return ResponseEntity.ok(novo);
    }

    @PutMapping("/{id}")
    public Cidade atualizar(@PathVariable Long id, @RequestBody Cidade entity) {
            Cidade entidadeAtual = service.buscarOuFalhar(id);

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
