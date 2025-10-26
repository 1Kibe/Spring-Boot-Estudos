package com.ryan.food_delivery_api.exception.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    
    RECURSO_NAO_ENCONTRADO("/recurso_nao_encontrado","Recurso não Encontrado"),
    PARAMETRO_INVALIDO("/parametro_invalido","Parametro invalido"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem_incompreencivel", "Mensagem Incompreencivel"),
    ENTIDADE_NAO_ENCONTRADA("/entidade_nao_encontrada", "Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade_em_uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro_negocio", "Violação de regra de negóçio");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://r_kibe.com.br" + path;
        this.title = title;
    }
}
