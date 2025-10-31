package com.ryan.food_delivery_api.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal subTotal;

    @Column(nullable = false)
    private BigDecimal taxaFrete;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id",nullable = false)
    private Usuario cliente;

    @Column(nullable = false)
    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataConfirmacao;

    private OffsetDateTime dataCancelamento;

    private OffsetDateTime dataEntrega;

    @Embedded
    private Endereco endereco;
    
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido = StatusPedido.CRIADO;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedidos = new ArrayList<>();

    @ManyToOne
    private Restaurante restaurante;


    // ===
    public void calcularValorTotal(){
        getItensPedidos().forEach(ItemPedido::calcularPrecoTotal);

        this.subTotal = getItensPedidos().stream()
        .map(itensPedidos -> itensPedidos.getPrecoTotal())
        .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = this.subTotal.add(this.taxaFrete);
    }

    public void definirFrete(){
        setTaxaFrete(getRestaurante().getTaxaFrete());
    }

    public void atribuirPedidos(){
        getItensPedidos().forEach(itensPedidos -> itensPedidos.setPedido(this));
    }

    // ===


}
