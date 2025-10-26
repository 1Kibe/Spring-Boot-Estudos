package com.ryan.food_delivery_api.domain.mixin.core;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ryan.food_delivery_api.domain.Restaurante;
import com.ryan.food_delivery_api.domain.mixin.RestauranteMixin;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule(){
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
    }
}
