package com.ryan.food_delivery_api.exception.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class ModeloLayout {
    //layout da resposta do body customizado
    private OffsetDateTime timestamp;
    private Integer status;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private List<Propriedade> propriedades;

@Getter
@Builder
    public static class Propriedade{
        private String name;
        private String userMessage;
    }
}
