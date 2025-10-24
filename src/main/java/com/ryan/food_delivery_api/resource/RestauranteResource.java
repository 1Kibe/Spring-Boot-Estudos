package com.ryan.food_delivery_api.resource;

import java.util.List;
import java.util.Optional;

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

import com.ryan.food_delivery_api.domain.Restaurante;
import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;
import com.ryan.food_delivery_api.service.RestauranteService;


@RestController
@RequestMapping("/restaurantes")
public class RestauranteResource {

    @Autowired
    private RestauranteService service;

    public RestauranteResource(RestauranteService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Restaurante> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> findById(@PathVariable Long id) {
        Optional<Restaurante> obj = service.buscar(id);
        
        if(obj.isPresent()){
            return ResponseEntity.ok(obj.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Restaurante obj) {
        try{
            obj = service.salvar(obj);

            return ResponseEntity.status(HttpStatus.CREATED)
                        .body(obj);
        }catch(EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //@PutMapping("/{id}")
    //public ResponseEntity<Restaurante> atualizar(@PathVariable Long id, @RequestBody Restaurante obj) {
    //    return service.buscar(id)
    //            .map(existente -> {
    //                obj.setId(id);
    //                Restaurante atualizado = service.salvar(obj);
    //                return ResponseEntity.ok(atualizado);
    //            })
    //            .orElse(ResponseEntity.notFound().build());
    //}

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante entity) {
        try{
            Restaurante entidadeAtual = service.buscar(id).orElse(null);

            if(entidadeAtual != null){
                BeanUtils.copyProperties(entity, entidadeAtual,
                "id","formasPagameto","endereco","dataCriacao","dataAtualizacao");

                entidadeAtual = service.salvar(entidadeAtual);
                return ResponseEntity.ok(entidadeAtual);
            }
            return ResponseEntity.notFound().build();
        }catch(EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deletar(@PathVariable Long id) {
    // if (service.buscar(id).isPresent()) {
    // service.deletar(id);
    // return ResponseEntity.noContent().build();
    // }
    // return ResponseEntity.notFound().build();
    // }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){
            service.deletar(id);
    }

}
