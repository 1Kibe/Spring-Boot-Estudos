package com.ryan.food_delivery_api.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo;

    @ManyToOne
    private Restaurante restaurante;

    @ManyToOne
    private ItemPedido itemPedido;


    public BigDecimal calularPrecoTotal(BigDecimal preco, Integer quantidade) {
        if (preco != null) {
            if (quantidade == null) {
                quantidade = 0;
            }
            preco = preco.multiply(BigDecimal.valueOf(quantidade));
            return preco;
        }

        return new BigDecimal(0);
    }
}
