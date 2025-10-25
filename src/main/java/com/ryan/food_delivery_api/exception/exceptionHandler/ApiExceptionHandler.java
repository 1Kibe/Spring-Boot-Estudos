package com.ryan.food_delivery_api.exception.exceptionHandler;

import java.time.OffsetDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ryan.food_delivery_api.exception.EntidadeEmUsoException;
import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;
import com.ryan.food_delivery_api.exception.NegocioException;

@ControllerAdvice // define que todas as exceptions de todos os controllers do projeto serao
                  // tratadas aqui
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e,
            WebRequest request) {

        HttpStatus _status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        String _detail = e.getMessage();

        //cria um body com build
        ModeloLayout layoutBodyy = createModeloLayoutBuilder(_status, problemType,_detail).build();

        
        return handleExceptionInternal(e, layoutBodyy, new HttpHeaders(), _status, request);
    }


    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException e, WebRequest request) {

        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {

        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // --------------------------------------------------------------------------------------------------

    // retorna um builder pronto
    private ModeloLayout.ModeloLayoutBuilder createModeloLayoutBuilder(HttpStatus _status, ProblemType problemType,
            String _detail) {
        return ModeloLayout.builder()
                .status(_status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(_detail);
    }

    // --------------------------------------------------------------------------------------------------

    // Customiza a resposta pradrao do ResponseEntityExceptionHandler
    @Override
    @Nullable
    protected ResponseEntity<Object> handleExceptionInternal(Exception arg0, @Nullable Object arg1, HttpHeaders arg2,
            HttpStatusCode arg3, WebRequest arg4) {

        if (arg1 == null) {
            arg1 = ModeloLayout.builder()
                    .dataHora(OffsetDateTime.now())
                    .status(arg3.value())
                    .title(((HttpStatus) arg3).getReasonPhrase())
                    .build();

        } else if (arg1 instanceof String) {
            arg1 = ModeloLayout.builder()
                    .dataHora(OffsetDateTime.now())
                    .status(arg3.value())
                    .title(((String) arg1))
                    .build();
        }

        return super.handleExceptionInternal(arg0, arg1, arg2, arg3, arg4);
    }
}
