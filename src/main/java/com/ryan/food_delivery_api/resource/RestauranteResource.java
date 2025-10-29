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

import com.ryan.food_delivery_api.domain.Restaurante;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.restaurante.RestauranteDtoAssembler;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.restaurante.RestauranteDtoDisassembler;
import com.ryan.food_delivery_api.domain.dto.restaurante.RestauranteDto;
import com.ryan.food_delivery_api.domain.dto.restaurante.RestauranteInputDto;
import com.ryan.food_delivery_api.exception.NegocioException;
import com.ryan.food_delivery_api.exception.cidade.CidadeNaoEncontradaException;
import com.ryan.food_delivery_api.exception.cozinha.CozinhaNaoEncontradaException;
import com.ryan.food_delivery_api.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteResource {

    @Autowired
    private RestauranteService service;

    @Autowired
    private RestauranteDtoAssembler restauranteDtoAssembler;

    @Autowired
    private RestauranteDtoDisassembler restauranteDtoDisassembler;

    public RestauranteResource(RestauranteService service) {
        this.service = service;
    }

    @GetMapping()
    public List<RestauranteDto> listar() {
        return restauranteDtoAssembler.toCollectionModel(service.listar());
    }

    @GetMapping("/{id}")
    public RestauranteDto findById(@PathVariable Long id) {
        Restaurante obj = service.buscarOuFalhar(id);
        return restauranteDtoAssembler.toModel(obj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDto salvar(@RequestBody RestauranteInputDto obj) {
        try {
            Restaurante restaurante = restauranteDtoDisassembler.toDomainObject(obj);

            return restauranteDtoAssembler.toModel(restaurante);
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestauranteDto atualizar(@PathVariable Long id, @RequestBody RestauranteInputDto input) {
        try {

            Restaurante entidadeAtual = service.buscarOuFalhar(id);

            // Com ModelMapper
            restauranteDtoDisassembler.copyToDomainObject(input, entidadeAtual);
 
            // Com BeanUtils
            // Restaurante restaurante = restauranteDtoDisassembler.toDomainObject(input);
            // BeanUtils.copyProperties(restaurante, entidadeAtual,
            // "id", "formasPagameto", "endereco", "dataCriacao", "dataAtualizacao");

            return restauranteDtoAssembler.toModel(service.salvar(entidadeAtual));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    //Sub Rotas


    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long id){
        service.ativar(id);
    }

    @DeleteMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativar(@PathVariable Long id){
        service.desativar(id);
    }


    // @PatchMapping("/{id}")
    // public Restaurante atualizarParcial(@PathVariable Long id, @RequestBody
    // Map<String, Object> campos,
    // HttpServletRequest request) {
    // Restaurante restauranteAtual = service.buscarOuFalhar(id);
    //
    // merge(campos, restauranteAtual, request);
    // return atualizar(id, restauranteAtual);
    // }

    // private void merge(Map<String, Object> dadosOrigem, Restaurante
    // restauranteDestino, HttpServletRequest request) {
    //
    // ServletServerHttpRequest serverHttpRequest = new
    // ServletServerHttpRequest(request);
    //
    // try {
    // ObjectMapper objectMapper = new ObjectMapper();
    //
    // // Configurações do Jackson (boa prática)
    // objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,
    // true);
    // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
    // true);
    //
    // // 1. Converte o Map para um objeto temporário
    // Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem,
    // Restaurante.class);
    //
    // // 2. Itera e copia os campos
    // dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
    //
    // // Verifica se o campo existe antes de usá-lo!
    // Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
    //
    // if (field != null) { // Verifica se field não é nulo
    // field.setAccessible(true);
    //
    // Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
    //
    // ReflectionUtils.setField(field, restauranteDestino, novoValor);
    // }
    // });
    // } catch (IllegalArgumentException e) {
    // Throwable rootCause = ExceptionUtils.getRootCause(e);
    //
    // throw new HttpMessageNotReadableException(e.getMessage(), rootCause,
    // serverHttpRequest);
    // }
    //
    // }

}
