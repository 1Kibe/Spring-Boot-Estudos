package com.ryan.Conceitos.Exceptions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//extenda RuntimeException
//anote a resposta desejada no ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND) 
public class EntidadeNaoEncontradaException{
    //extends com ResponseStatusException
    //gera um superconstrutor
    
    //public EntidadeNaoEncontradaException(HttpStatus status, String mensagem){
    //    super(status, mensagem);
    //}


    /*no service agora é possivel passsa um status diferente junto com a msg(NAO RECOMENDADO FAZER EM UMA CLASSSE SERVICE)

     * public void deletar(Long id){
        //verificador
        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException(HttpStatus.BAD_QUEST, "Restaurante de ID " + id + " não encontrado.");
        }
        repository.deleteById(id);
    }
     */
    
    //----------------------------------------------------------------------------------

    //export com RunTimeExecption
    //public EntidadeNaoEncontradaException(String mensagem){
    //    //super(mensagem);
    //    this(HttpStatus.NOT_FOUND, mensagem);
    //}

    //pronta para ser usado em resource
    /*metodo usado DELETE
     * @DeleteMapping("/{id}")
     * @ResponseStatus(HttpStatus.NO_CONTENT)
     * public void remover(@PathVariable Long id){
     * service.excluir(id);
     * }
     */
    //

    
}
