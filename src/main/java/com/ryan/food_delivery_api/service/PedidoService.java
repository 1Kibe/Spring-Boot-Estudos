package com.ryan.food_delivery_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.food_delivery_api.domain.Cidade;
import com.ryan.food_delivery_api.domain.FormaPagamento;
import com.ryan.food_delivery_api.domain.Pedido;
import com.ryan.food_delivery_api.domain.Produto;
import com.ryan.food_delivery_api.domain.Restaurante;
import com.ryan.food_delivery_api.domain.Usuario;
import com.ryan.food_delivery_api.exception.NegocioException;
import com.ryan.food_delivery_api.exception.pedido.PedidoNaoEncontradoException;
import com.ryan.food_delivery_api.repository.PedidoRepository;

import jakarta.transaction.Transactional;

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
    public Pedido emitir(Pedido pedido) {
        validarPedido(pedido);
        validarItens(pedido);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularValorTotal();

        return repository.save(pedido);
    }

    private void validarPedido(Pedido pedido) {
        Cidade cidade = cidadeService.buscarOuFalhar(pedido.getEndereco().getCidade().getId());
        Usuario cliente = usuarioService.buscarOuFalhar(pedido.getCliente().getId());
        Restaurante restaurante = restauranteService.buscarOuFalhar(pedido.getRestaurante().getId());
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(pedido.getFormaPagamento().getId());

        pedido.getEndereco().setCidade(cidade);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);

        if (restaurante.naoAceitarFormaPagamento(formaPagamento)) {
            throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                    formaPagamento.getDescricao()));
        }
    }


    private void validarItens(Pedido pedido) {
        pedido.getItensPedidos().forEach(item -> {
            Produto produto = produtoService.buscarOuFalhar(
                    pedido.getRestaurante().getId(), item.getProduto().getId());

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
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
