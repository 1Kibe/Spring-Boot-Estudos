package com.ryan.Conceitos.QueryMethods.CriteriaAPI;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CodeRepositoryImpl {

    @PersistenceContext
    private EntityManager manager;

    //public List<Entidade> NomeQuaquer(String nome) {
    // CriteriaBuilder builder = manager.getCriteriaBUilder();                                  //CriteriaBuilder é usado para construir a query de forma programatica

    // CriteriaQuery<Entidade> criteria = builder.createQuery(Entidade.class);                  //CriteriaQuery é responsavel pelas clausulas da query exemplo: select, from, where

    //Roote<Entidade> criteria.from(Entidade.class);                                            //from Entidade, Root é a raiz da query, ou seja, a entidade que está sendo consultada


    //Predicate nomePredicate = builder.like(root.get("nome"), "%" + nome + "%");               //Predicate é a clausula where, nesse caso where nome like %:nome%

    //criteria.where(nomePredicate);                                                            //adiciona a clausula where na query com o predicate criado


    //TypedQuery<Entidade> query = manager.createQuery(criteria);                               //TypedQuery é a query que vai ser executada
    //return query.getResultList();
    //}


    //linha 15,17 e 19 Sao formas padrao de iniciar uma query com Criteria API
    //linha 27 e 28 Sao formas padrao de  retornar o resultado da query

    //Linha 22 sendo uma criacao de um Predicate para a clausula where
    //from Entidade where nome like %:nome%
    //'from Entidade' sendo de Root e 'where' da linha 24 sendo de CriteriaQuery

    //------------------------------------------------------------------------------------------------

    //Dinamico

    //@PersistenceContext
    //private EntityManager manager;


    //public List<Entidade> NomeQuaquer(String nome) {

    // CriteriaBuilder builder = manager.getCriteriaBUilder();                                  //CriteriaBuilder é usado para construir a query de forma programatica
    // CriteriaQuery<Entidade> criteria = builder.createQuery(Entidade.class);                  //CriteriaQuery é responsavel pelas clausulas da query exemplo: select, from, where
    //Roote<Entidade> criteria.from(Entidade.class);                                            //from Entidade, Root é a raiz da query, ou seja, a entidade que está sendo consultada

    //var predicates = new ArrayList<Predicate>();                                              //lista de predicates para adicionar os filtros dinamicos

    //if (SpringUtils.hasLength(nome)) {                                                        //SpringUtils.hasLength(nome);  verifica se a string nao é nula nem vazia
    //      predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));                   //adiciona o predicate na lista
    //}

    // outros filtros...

    
    //criteria.where(predicates.toArray(new Predicate[0]));                                     //ToArray converte a lista de predicates para um array de predicates 


    //TypedQuery<Entidade> query = manager.createQuery(criteria);                               //TypedQuery é a query que vai ser executada
    //return query.getResultList();

    //}
}
