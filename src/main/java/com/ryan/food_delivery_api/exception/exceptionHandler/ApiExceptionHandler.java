package com.ryan.food_delivery_api.exception.exceptionHandler;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ryan.food_delivery_api.exception.EntidadeEmUsoException;
import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;
import com.ryan.food_delivery_api.exception.NegocioException;

@ControllerAdvice // define que todas as exceptions de todos os controllers do projeto serao tratadas aqui
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

    //customiza o body de resposta
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e){
        ModeloLayout layoutBodyy = ModeloLayout.builder()
            .dataHora(OffsetDateTime.now())
            .mensagem(e.getMessage())
            .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(layoutBodyy);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException e){
        ModeloLayout layoutBodyy = ModeloLayout.builder()
            .dataHora(OffsetDateTime.now())
            .mensagem(e.getMessage())
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(layoutBodyy);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e){
        ModeloLayout layoutBodyy = ModeloLayout.builder()
            .dataHora(OffsetDateTime.now())
            .mensagem(e.getMessage())
            .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(layoutBodyy);
    }

    //Customiza a resposta pradrao do ResponseEntityExceptionHandler
    //@Override
    //@Nullable
    //protected ResponseEntity<Object> handleExceptionInternal(Exception arg0, @Nullable Object arg1, HttpHeaders arg2,
    //        HttpStatusCode arg3, WebRequest arg4) {
    //    arg1 = ModeloLayout.builder()
    //        .dataHora(OffsetDateTime.now())
    //        .mensagem(arg0.getMessage())
    //        .build();
    //    return super.handleExceptionInternal(arg0, arg1, arg2, arg3, arg4);
    //}
}
