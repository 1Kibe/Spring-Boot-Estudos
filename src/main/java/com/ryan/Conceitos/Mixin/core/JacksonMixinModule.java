package com.ryan.Conceitos.Mixin.core;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.module.SimpleModule;

//Classe de atribuicao
@Component
public class JacksonMixinModule extends SimpleModule {

    // Aqui você já seta todos os mixins que quiser
    // setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
    // setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    // setMixInAnnotation(Endereco.class, EnderecoMixin.class);

    // Se quiser, pode adicionar mais conforme a necessidade
}
