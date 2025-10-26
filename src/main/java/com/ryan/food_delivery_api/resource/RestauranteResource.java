package com.ryan.food_delivery_api.resource;

import com.fasterxml.jackson.databind.DeserializationFeature;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryan.food_delivery_api.domain.Restaurante;
import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;
import com.ryan.food_delivery_api.exception.NegocioException;
import com.ryan.food_delivery_api.service.RestauranteService;

import jakarta.servlet.http.HttpServletRequest;

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
    public Restaurante findById(@PathVariable Long id) {
        return service.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante salvar(@RequestBody Restaurante obj) {
        try {
            return service.salvar(obj);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Restaurante atualizar(@PathVariable Long id, @RequestBody Restaurante entity) {
        Restaurante entidadeAtual = service.buscarOuFalhar(id);

        BeanUtils.copyProperties(entity, entidadeAtual,
                "id", "formasPagameto", "endereco", "dataCriacao", "dataAtualizacao");

        return service.salvar(entidadeAtual);

    }

    @PatchMapping("/{id}")
    public Restaurante atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos, HttpServletRequest request){
        Restaurante restauranteAtual = service.buscarOuFalhar(id);

        merge(campos, restauranteAtual, request);
        return atualizar(id, restauranteAtual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {

        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Configurações do Jackson (boa prática)
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            // 1. Converte o Map para um objeto temporário
            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

            // 2. Itera e copia os campos
            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {

                // Verifica se o campo existe antes de usá-lo!
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);

                if (field != null) { // Verifica se field não é nulo
                    field.setAccessible(true);

                    Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

                    ReflectionUtils.setField(field, restauranteDestino, novoValor);
                }
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);

            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }

    }

}
