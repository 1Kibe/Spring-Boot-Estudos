package com.ryan.Conceitos.Exceptions.ExceptionHandler.ExceptionHandlerGlobal;


//  Ao inves de criar Instancias separadas de ExceptionHandler em cada controler,
//ao colocar aqui todos os controller usao esse formato de resposta


//@ControllerAdvice // define que todas as exceptions de todos os controllers do projeto serao tratadas aqui
public class CodeApiExceptionHandler {

    ////customiza o body de resposta
    //@ExceptionHandler(EntidadeNaoEncontradaException.class)
    //public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e){
    //    ModeloLayout layoutBodyy = ModeloLayout.builder()
    //        .dataHora(OffsetDateTime.now())
    //        .mensagem(e.getMessage())
    //        .build();

    //    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(layoutBodyy);
    //}

    //@ExceptionHandler(NegocioException.class)
    //public ResponseEntity<?> tratarNegocioException(NegocioException e){
    //    ModeloLayout layoutBodyy = ModeloLayout.builder()
    //        .dataHora(OffsetDateTime.now())
    //        .mensagem(e.getMessage())
    //        .build();

    //    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(layoutBodyy);
    //}
}
