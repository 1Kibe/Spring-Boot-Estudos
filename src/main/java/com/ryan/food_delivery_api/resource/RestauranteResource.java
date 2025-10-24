package com.ryan.food_delivery_api.resource;

import java.util.List;
import java.util.Optional;

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
        Optional<Restaurante> cozinha = service.buscar(id);
        return cozinha.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Restaurante> salvar(@RequestBody Restaurante obj) {
        Restaurante novo = service.salvar(obj);
        return ResponseEntity.ok(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long id, @RequestBody Restaurante obj) {
        /*
         * try {
         * Optional<Restaurante> optAtual = service.buscar(id);
         * if (optAtual.isPresent()) {
         * Restaurante restauranteAtual = optAtual.get();
         * 
         * // copia propriedades do request para a entidade atual, excluindo campos que
         * não devem ser sobrescritos
         * BeanUtils.copyProperties(restaurante, restauranteAtual, "id",
         * "formasPagamento", "endereco","dataCriacao");
         * 
         * Restaurante atualizado = service.salvar(restauranteAtual);
         * return ResponseEntity.ok(atualizado);
         * }
         * return ResponseEntity.notFound().build();
         * } catch (EntidadeNaoEncontradaException e) {
         * return ResponseEntity.badRequest().body(e.getMessage());
         * }
         * 
         * //* Preserva um objeto existente: busca restauranteAtual
         * (repository.findById(...)), copia
         * //propriedades do request para o objeto existente e exclui campos críticos
         * ("id", "formasPagamento", "endereco").
         * 
         * //* BeanUtils.copyProperties do Spring copia nulls — se um campo for null no
         * request ele sobrescreve o
         * //existente. Para copiar apenas não-nulos use utilitário custom ou MapStruct
         * (ou implemente merge manual).
         * 
         */
        return service.buscar(id)
                .map(existente -> {
                    obj.setId(id);
                    Restaurante atualizado = service.salvar(obj);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
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
