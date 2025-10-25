package com.ryan.food_delivery_api.exception.exceptionHandler;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ModeloLayout {
    //layout da resposta do body customizado

    private OffsetDateTime dataHora;
    private String mensagem;
}
