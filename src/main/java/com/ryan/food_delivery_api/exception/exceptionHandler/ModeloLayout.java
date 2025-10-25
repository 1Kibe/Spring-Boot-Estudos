package com.ryan.food_delivery_api.exception.exceptionHandler;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class ModeloLayout {
    //layout da resposta do body customizado
    private OffsetDateTime dataHora;
    private Integer status;
    private String type;
    private String title;
    private String detail;
}
