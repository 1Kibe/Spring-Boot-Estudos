package com.ryan.Conceitos.QueryMethods;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CodeRepositoryImpl implements CodeRepositoryQuery {
    
    //Customizando queries com EntityManager
    //Esse arquivo é usado quando a query é muito complexa para ser feita com query methods
    //ou quando se quer ter mais controle sobre a query

    @PersistenceContext
    private EntityManager manager;


    //public List<Entidade> NomeQuaquer(String nome) {
    //    var jpql = "FROM Entidade WHERE nome LIKE %:nome%";
    
    //    return manager.createQuery(jpql, Entidade.class)
    //                  .setParameter("nome", nome)
    //                  .getResultList();
    //}

    //modo de uso 
    //declarar o metodo no repository que ele implementa e chamar aqui dentro, sem anotações
    //

    //------------------------------------------------------------------------------------------------

    //Outra forma de externalizar queries é criar um arquivo ClasseRepositoryQuery
    //declarar o metodo na interface ClasseRepositoryQuery
    //fazer a interface ClasseRepository extender a ClasseRepositoryQuery
    //fazer a classe ClasseRepositoryImpl implementar a ClasseRepositoryQuery

    //assim quando o metodo for chamado na ClasseRepository ele vai ser implementado na ClasseRepositoryImpl
    //explicacao em package CriteriaAPI
}
