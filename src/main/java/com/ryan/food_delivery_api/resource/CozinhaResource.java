package com.ryan.food_delivery_api.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ryan.food_delivery_api.domain.Cozinha;
import com.ryan.food_delivery_api.service.CozinhaService;

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



@RestController
@RequestMapping("/cozinhas")
public class CozinhaResource {

    @Autowired
    private CozinhaService service;

    public CozinhaResource(CozinhaService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Cozinha> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Cozinha findById(@PathVariable Long id){
        return service.buscarOuFalhar(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha salvar(@RequestBody Cozinha obj) {
        return service.salvar(obj);
    }

    @PutMapping("/{id}")
    public Cozinha atualizar(@PathVariable Long id, @RequestBody Cozinha entity) {
            Cozinha entidadeAtual = service.buscarOuFalhar(id);

            BeanUtils.copyProperties(entity, entidadeAtual,
            "id","formasPagameto","endereco","dataCriacao","dataAtualizacao");

            return service.salvar(entidadeAtual);
            
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.buscar(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    
    
}
