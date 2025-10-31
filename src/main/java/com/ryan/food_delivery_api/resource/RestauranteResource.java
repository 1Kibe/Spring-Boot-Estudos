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
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.formaPagamento.FormaPagamentoDtoAssembler;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.restaurante.RestauranteDtoAssembler;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.restaurante.RestauranteDtoDisassembler;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.usuario.UsuarioDtoAssembler;import com.ryan.food_delivery_api.domain.dto.formaPagamento.FormaPagamentoDto;
import com.ryan.food_delivery_api.domain.dto.restaurante.RestauranteDto;
import com.ryan.food_delivery_api.domain.dto.restaurante.RestauranteInputDto;
import com.ryan.food_delivery_api.domain.dto.usuario.UsuarioDto;
import com.ryan.food_delivery_api.exception.NegocioException;
import com.ryan.food_delivery_api.exception.cidade.CidadeNaoEncontradaException;
import com.ryan.food_delivery_api.exception.cozinha.CozinhaNaoEncontradaException;
import com.ryan.food_delivery_api.service.RestauranteService;
import com.ryan.food_delivery_api.service.UsuarioService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteResource {

    @Autowired
    private RestauranteService service;

    @Autowired
    private RestauranteDtoAssembler assembler;

    @Autowired
    private RestauranteDtoDisassembler disassembler;

    // ===

    @Autowired
    private FormaPagamentoDtoAssembler formaPagamentoDtoAssembler;

    @Autowired
    private UsuarioDtoAssembler usuarioDtoAssembler;

    // ++++
    @Autowired
    private UsuarioService usuarioService;
    // ++++

    // ===

    @GetMapping()
    public List<RestauranteDto> listar() {
        return assembler.toCollectionModel(service.listar());
    }

    @GetMapping("/{id}")
    public RestauranteDto findById(@PathVariable Long id) {
        Restaurante obj = service.buscarOuFalhar(id);
        return assembler.toModel(obj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDto salvar(@RequestBody RestauranteInputDto obj) {
        try {
            Restaurante restaurante = disassembler.toDomainObject(obj);

            return assembler.toModel(restaurante);
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestauranteDto atualizar(@PathVariable Long id, @RequestBody RestauranteInputDto input) {
        try {

            Restaurante entidadeAtual = service.buscarOuFalhar(id);

            // Com ModelMapper
            disassembler.copyToDomainObject(input, entidadeAtual);

            // Com BeanUtils
            // Restaurante restaurante = disassembler.toDomainObject(input);
            // BeanUtils.copyProperties(restaurante, entidadeAtual,
            // "id", "formasPagameto", "endereco", "dataCriacao", "dataAtualizacao");

            return assembler.toModel(service.salvar(entidadeAtual));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    // Sub Rotas

    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long id) {
        service.ativar(id);
    }

    @DeleteMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativar(@PathVariable Long id) {
        service.desativar(id);
    }

    // ============================================

    @GetMapping("/{id}/forma-de-pagamento")
    public List<FormaPagamentoDto> listarFormasPagamentos(@PathVariable Long id) {
        Restaurante entity = service.buscarOuFalhar(id);

        return formaPagamentoDtoAssembler.toCollectionModel(entity.getFormaPagamento());
    }

    @DeleteMapping("/{id}/forma-de-pagamento/{id2}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerFormasPagamentos(@PathVariable Long id, @PathVariable Long id2) {
        service.removerFormaPagamento(id, id2);
    }

    @PutMapping("/{id}/forma-de-pagamento/{id2}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionarFormasPagamentos(@PathVariable Long id, @PathVariable Long id2) {
        service.adicionarFormaPagamento(id, id2);
    }

    // ============================================

    @PutMapping("/{id}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrirRestaurante(@PathVariable Long id) {
        service.abertura(id);
    }

    @PutMapping("/{id}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fecharRestaurante(@PathVariable Long id) {
        service.fechamento(id);
    }
    // ============================================

    @GetMapping("/{id}/responsaveis")
    public List<UsuarioDto> listarReponsavel(@PathVariable Long id){
        Restaurante restaurante = service.buscarOuFalhar(id);
        return usuarioDtoAssembler.toCollectionModel(restaurante.getResponsaveis());
    }

    @PutMapping("/{id}/responsaveis/{idR}")
    public void vincularResponsavel(@PathVariable Long id, @PathVariable Long idR) {
        usuarioService.vincularResponsavel(id, idR);
    }

    @DeleteMapping("/{id}/responsaveis/{idR}")
    public void desvincularResponsavel(@PathVariable Long id, @PathVariable Long idR) {
        usuarioService.desvincularResponsavel(id, idR);
    }
    // ============================================

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
