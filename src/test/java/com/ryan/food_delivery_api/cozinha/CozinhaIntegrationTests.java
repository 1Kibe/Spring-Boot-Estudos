package com.ryan.food_delivery_api.cozinha;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

import com.ryan.food_delivery_api.domain.Cozinha;
import com.ryan.food_delivery_api.repository.CozinhaRepository;
import com.ryan.food_delivery_api.service.CozinhaService;

public class CozinhaIntegrationTests {

    @Autowired
    private CozinhaService service;

    @Autowired
    private CozinhaRepository repository;

    //@Test
    public void testarCadastroSucesso(){
        //cenario
        Cozinha nova = new Cozinha();
        nova.setNome("Japoneza");

        //acao
        nova = service.salvar(nova);

        //validacao
        assertThat(nova).isNotNull();
        assertThat(nova.getId()).isNotNull();

    }

    @Test()
    public void testarCadastroFalhar_QuandoSemNome(){
        //cenario
        Cozinha nova = new Cozinha();
        nova.setNome(null);

        //acao + verificacao de exception
        assertThrows(NullPointerException.class, () -> {
            service.salvar(nova);
            repository.flush();
        });

    }
}
