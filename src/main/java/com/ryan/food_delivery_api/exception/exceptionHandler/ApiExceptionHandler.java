package com.ryan.food_delivery_api.exception.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.ryan.food_delivery_api.exception.EntidadeEmUsoException;
import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;
import com.ryan.food_delivery_api.exception.NegocioException;

@ControllerAdvice // define que todas as exceptions de todos os controllers do projeto serao
                  // tratadas aqui
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Erro inesperado no sistema, contactar o suporte";

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e,
            WebRequest request) {

        HttpStatus _status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        String _detail = e.getMessage();

        // cria um body com build
        ModeloLayout layoutBodyy = createModeloLayoutBuilder(_status, problemType, _detail)
        .userMessage(_detail)
        .build();

        return handleExceptionInternal(e, layoutBodyy, new HttpHeaders(), _status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException e, WebRequest request) {

        HttpStatus _status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String _detail = e.getMessage();

        ModeloLayout layoutBodyy = createModeloLayoutBuilder(_status, problemType, _detail)
        .userMessage(_detail)
        .build();

        return handleExceptionInternal(e, layoutBodyy, new HttpHeaders(), _status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {

        HttpStatus _status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String _detail = e.getMessage();

        ModeloLayout layoutBodyy = createModeloLayoutBuilder(_status, problemType, _detail)
        .userMessage(_detail)
        .build();

        return handleExceptionInternal(e, layoutBodyy, new HttpHeaders(), _status, request);
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
    // Handler
    // Formas Padrao
    // --------------------------------------------------------------------------------------------------

    // qualquer exception nao tratada
    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> hendleUncaught(Exception ex, WebRequest request) {

        HttpStatus _status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
        String _detail = MSG_ERRO_GENERICA_USUARIO_FINAL;

        // Imprime o rastreamento completo da pilha da exceção (stack trace) no console
        // do servidor.
        // Útil para diagnóstico e depuração, mostrando a causa raiz e a sequência de
        // chamadas.
        ex.printStackTrace();

        ModeloLayout layoutBodyy = createModeloLayoutBuilder(_status, problemType, _detail).build();

        return handleExceptionInternal(ex, layoutBodyy, new HttpHeaders(), _status, request);
    }
    // --------------------------------------------------------------------------------------------------

    // Para erros gerais de sintaxe no json
    @Override
    @Nullable
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        // pega a exception raiz do erro
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        // se a causa for um formato invalido. Exemplo "id":"1,"
        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return hendlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        HttpStatus _status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String _detail = "O corpo da requisição esta invalido. Verifique erro de sintaxe";

        ModeloLayout layoutBodyy = createModeloLayoutBuilder(_status, problemType, _detail)
        .userMessage(_detail)
        .build();

        return handleExceptionInternal(ex, layoutBodyy, new HttpHeaders(), _status, request);
    }    

    // --------------------------------------------------------------------------------------------------
    private String joinPath(List<Reference> references) {
        // Constrói o caminho completo do campo do JSON que está inválido (ex:
        // "cidade.id").
        return references.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
    }
    // --------------------------------------------------------------------------------------------------

    // trata propriedades ignoradas(anotadas com @JsonIgnore) ou à mais nas
    // requisicoes
    private ResponseEntity<Object> hendlePropertyBindingException(PropertyBindingException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String path = joinPath(ex.getPath());

        HttpStatus _status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String _detail = String.format("A propriedade '%s' não existe.", path);

        ModeloLayout layoutBodyy = createModeloLayoutBuilder(_status, problemType, _detail)
        .userMessage(_detail)
        .build();

        return handleExceptionInternal(ex, layoutBodyy, headers, _status, request);
    }

    // --------------------------------------------------------------------------------------------------

    // Trata formatos invalido. Exemplo "id":"aa"
    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String path = joinPath(ex.getPath());

        HttpStatus _status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String _detail = String.format("A propriedade '%s' recebeu o valor '%s', "
                + "que é de um tipo inválido. Tipo esperado: %s", path, ex.getValue(),
                ex.getTargetType().getSimpleName());

        ModeloLayout layoutBodyy = createModeloLayoutBuilder(_status, problemType, _detail)
        .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
        .build();

        return handleExceptionInternal(ex, layoutBodyy, headers, status, request);
    }

    // --------------------------------------------------------------------------------------------------

    // Metodo para Captura,
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    // -----------

    // trata erro de '/id' invalido. Exemplo rota/ad
    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String path = ex.getName();

        HttpStatus _status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;

        @SuppressWarnings("null")
        String _detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
                + "que é de um tipo inválido. Tipo esperado: %s.",
                path, ex.getValue(),
                ex.getRequiredType().getSimpleName());

        ModeloLayout layoutBodyy = createModeloLayoutBuilder(_status, problemType, _detail)
        .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
        .build();

        // Lembre-se de usar o status definido (_status), não o status que veio como
        // argumento do Spring,
        // para que seu layoutBodyy seja usado corretamente.
        return handleExceptionInternal(ex, layoutBodyy, headers, _status, request);
    }

    // -------------------------------------------------------------------------------------------

    // trata url invalida
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String path = ex.getRequestURL();

        HttpStatus _status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;

        String _detail = String.format("O recurso '%s', que vocẽ tentou acessar, é inexistente.", path);

        ModeloLayout layoutBodyy = createModeloLayoutBuilder(_status, problemType, _detail)
        .userMessage(_detail)
        .build();

        return handleExceptionInternal(ex, layoutBodyy, headers, _status, request);
    }

    // --------------------------------------------------------------------------------------------------

    // Customiza a resposta pradrao do ResponseEntityExceptionHandler
    @Override
    @Nullable
    protected ResponseEntity<Object> handleExceptionInternal(Exception arg0, @Nullable Object arg1, HttpHeaders arg2,
            HttpStatusCode arg3, WebRequest arg4) {

        if (arg1 == null) {
            arg1 = ModeloLayout.builder()
                    .timestamp(OffsetDateTime.now())
                    .status(arg3.value())
                    .title(((HttpStatus) arg3).getReasonPhrase())
                    .build();

        } else if (arg1 instanceof String) {
            arg1 = ModeloLayout.builder()
                    .timestamp(OffsetDateTime.now())
                    .status(arg3.value())
                    .title(((String) arg1))
                    .build();
        }

        return super.handleExceptionInternal(arg0, arg1, arg2, arg3, arg4);
    }
}
