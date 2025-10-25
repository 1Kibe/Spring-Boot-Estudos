package com.ryan.Conceitos.Exceptions.ExceptionHandler;

public class CodeResource {

    ////customiza o body de resposta
    //@ExceptionHandler(EntidadeNaoEncontradaException.class)
    //public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e){
    //    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    //}

    //@ExceptionHandler(NegocioException.class)
    //public ResponseEntity<?> tratarNegocioException(NegocioException e){
    //    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    //}

    //---------------------------------------------------------------------------------------------------

    //Customizando o body com Builder
    //nescessario uma classe com o layout desejado,exemplo em CodeProblema

    //@ExceptionHandler(EntidadeNaoEncontradaException.class)
    //public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e){
    //    Problema layoutBodyy = Problema.builder()
    //        .dataHora(OffsetDateTime.now())
    //        .mensagem(e.getMessage())
    //        .build();

    //    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(layoutBodyy);
    //}
}
