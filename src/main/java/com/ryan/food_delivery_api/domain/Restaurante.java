package com.ryan.food_delivery_api.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Restaurante {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotNull
    //@NotEmpty
    //@NotBlank
    private String nome;

    //@DecimalMin("0")
    private BigDecimal taxaFrete;

    @Embedded       //chama o Embeddable
    private Endereco endereco;

    private Boolean ativo = Boolean.TRUE;

    @OneToMany(mappedBy = "restaurante") 
    private List<Produto> produtos = new ArrayList<>();

    @OneToMany(mappedBy = "restaurante")
    private List<Pedido> pedidos = new ArrayList<>();

    //@NotNull
    //@Valid
    @ManyToOne
    //@JsonIgnoreProperties(value = {"nome"},allowGetters = true) // ignora a propriedade nome da cozinha quando vor mostrar no json
    private Cozinha cozinha;

    //ATENCAO cuidado com a rota Put ao implementa @ManyToMany
    //ao fazer Put sem o tratamento adequado ele nao carrega os dados antigos se ja ouver algo, e acaba deletando os mesmos
    @ManyToMany
    @JoinTable( //customiza a table de juncao
        name = "restaurante_forma_pagamento",
        joinColumns = @JoinColumn(name = "restaurante_id"),                 //JoinCollun define o nome da coluna que faz referencia a classe que esta mapeando
        inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")       //define o nome da outra tabela 
    )
    private List<FormaPagamento> formaPagamento = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false)
    private OffsetDateTime dataCriacao;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime dataAtualizacao;

    

    public void ativar(){
        setAtivo(true);
    }
    
    public void desativar(){
        setAtivo(false);
    }

}
