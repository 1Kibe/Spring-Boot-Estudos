package com.ryan.Conceitos.Exceptions.ExceptionHandler.HandleExceptionInternal;

//@ControllerAdvice // define que todas as exceptions de todos os controllers do projeto serao
// tratadas aqui

// extends ResponseEntityExceptionHandler para customizar o body com as formas
// padroes inclusas
public class CodeApiExceptionHandlerInternal /* extends ResponseEntityExceptionHandler */ {

    // Customiza a resposta pradrao do ResponseEntityExceptionHandler
    // @Override
    // @Nullable
    // protected ResponseEntity<Object> handleExceptionInternal(Exception arg0,
    // @Nullable Object arg1, HttpHeaders arg2,
    // HttpStatusCode arg3, WebRequest arg4) {
    //
    // if (arg1 == null) {
    // arg1 = ModeloLayout.builder()
    // .dataHora(OffsetDateTime.now())
    // .status(arg3.value())
    // .title(((HttpStatus) arg3).getReasonPhrase())
    // .build();
    //
    // } else if (arg1 instanceof String) {
    // arg1 = ModeloLayout.builder()
    // .dataHora(OffsetDateTime.now())
    // .status(arg3.value())
    // .title(((String) arg1))
    // .build();
    // }
    //
    // return super.handleExceptionInternal(arg0, arg1, arg2, arg3, arg4);
    // }

    // modo de uso
    // @ExceptionHandler(EntidadeEmUsoException.class)
    // public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException
    // e, WebRequest request) {
    //
    // return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(),
    // HttpStatus.NOT_FOUND, request);
    // }
}
