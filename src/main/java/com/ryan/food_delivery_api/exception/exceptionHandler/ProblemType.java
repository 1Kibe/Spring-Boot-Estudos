package com.ryan.food_delivery_api.exception.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    ENTIDADE_NAO_ENCONTRADA("/entidade_nao_encontrada","Entidade não encontrada");

    private String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "https://r_kibe.com.br" + path;
        this.title = title;
    }
}
