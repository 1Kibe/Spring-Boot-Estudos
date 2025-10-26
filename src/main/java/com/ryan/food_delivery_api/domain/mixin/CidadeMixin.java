package com.ryan.food_delivery_api.domain.mixin;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ryan.food_delivery_api.domain.Estado;

@Component
public class CidadeMixin extends SimpleModule {

    @JsonIgnoreProperties(value = {"nome"},allowGetters = true)
    private Estado estado;
}
