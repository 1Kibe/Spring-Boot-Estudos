package com.ryan.food_delivery_api.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private String observacao;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;

    public void calcularPrecoTotal(){
        BigDecimal precoUnico = this.precoUnitario;
        Integer quantidade = this.quantidade;
        
        if(precoUnico == null) {
            precoUnico = BigDecimal.ZERO;
        }
        if(quantidade == null) {
            quantidade = 0;
        }

        this.setPrecoTotal(precoUnico.multiply(new BigDecimal(quantidade)));
    }
}
