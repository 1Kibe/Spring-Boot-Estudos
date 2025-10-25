package com.ryan.Conceitos.Exceptions.ExceptionHandler.HandleExceptionInternal;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL) //diz que as que sao null, nao seral incluidas
@Getter
@Builder
public class CodeModeloLayout {
    //layout da resposta do body customizado
    //private Integer status;
    //private String type;
    //private String title;
    //private String detail;
    //
//
    //private OffsetDateTime dataHora;
    //private String mensagem;
}
