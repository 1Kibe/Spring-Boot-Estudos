package com.ryan.food_delivery_api.service;

import com.ryan.food_delivery_api.domain.*;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.pedido.PedidoDtoAssembler;
import com.ryan.food_delivery_api.domain.dto.assemblersDisassemblers.pedido.PedidoDtoDisassembler;
import com.ryan.food_delivery_api.domain.dto.itemPedido.ItemPedidoInputDto;
import com.ryan.food_delivery_api.domain.dto.pedido.PedidoDto;
import com.ryan.food_delivery_api.domain.dto.pedido.PedidoInputDto;
import com.ryan.food_delivery_api.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.food_delivery_api.exception.NegocioException;
import com.ryan.food_delivery_api.exception.pedido.PedidoNaoEncontradoException;
import com.ryan.food_delivery_api.repository.PedidoRepository;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    // ===

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PedidoDtoAssembler assembler;

    @Autowired
    private PedidoDtoDisassembler disassembler;

    // ===

    @Transactional
    public Pedido buscarOuFalhar(Long id) {
        // ResponseStatus
        return repository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id));
    }

    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoService.buscarOuFalhar(restauranteId, produtoId);
    }


    @Transactional
    public void insert(PedidoInputDto input) {
        Pedido pedido = new Pedido();
        List<ItemPedido> itensPedido = new ArrayList<>();
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(input.getFormaPagamento().getId());
        Restaurante restaurante = restauranteService.buscarOuFalhar(input.getRestaurante().getId());
        Cidade cidade = cidadeService.buscarOuFalhar(input.getEndereco().getCidade().getId());
        BigDecimal sub = new BigDecimal(0);
        Endereco endereco = new Endereco();
        endereco.setCep(input.getEndereco().getCep());
        endereco.setLogradouro(input.getEndereco().getLogradouro());
        endereco.setNumero(input.getEndereco().getNumero());
        endereco.setComplemento(input.getEndereco().getComplemento());
        endereco.setBairro(input.getEndereco().getBairro());
        endereco.setCidade(cidade);

        pedido.setFormaPagamento(formaPagamento);
        pedido.setRestaurante(restaurante);
        pedido.setEndereco(endereco);

        //temp
        pedido.setCliente(new Usuario());
        pedido.getCliente().setId(1L);

        pedido.setTaxaFrete(restaurante.getTaxaFrete());
        pedido.setDataCriacao(OffsetDateTime.now());

        for (ItemPedidoInputDto item : input.getItensPedido()) {
            ItemPedido itemPedido = new ItemPedido();
            Produto produto = produtoService.buscarOuFalhar(input.getRestaurante().getId(), item.getProdutoId());
            itemPedido.setProduto(produto);
            itemPedido.setPedido(pedido);
            itemPedido.setQuantidade(item.getQuantidade());
            itemPedido.setPrecoUnitario(produto.getPreco());
            itemPedido.setObservacao(item.getObservacao());
            itemPedido.setPrecoTotal(produto.calularPrecoTotal(itemPedido.getPrecoUnitario(), item.getQuantidade()));
            sub = sub.add(itemPedido.getPrecoTotal());
            itensPedido.add(itemPedido);
        }

        pedido.setItensPedidos(itensPedido);
        pedido.setSubTotal(sub);
        pedido.setValorTotal(sub.add(restaurante.getTaxaFrete()));
        repository.save(pedido);
    }
    // Sub Rotas

    // @Transactional
    // public void ativar(Long id) {
    // Pedido entityAtual = buscarOuFalhar(id);
    // entityAtual.ativar();
    // }
    //
    // @Transactional
    // public void desativar(Long id) {
    // Pedido entityAtual = buscarOuFalhar(id);
    // entityAtual.desativar();
    // }

}
