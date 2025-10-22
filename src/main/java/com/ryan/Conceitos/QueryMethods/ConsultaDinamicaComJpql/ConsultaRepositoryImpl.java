package com.ryan.Conceitos.QueryMethods.ConsultaDinamicaComJpql;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ConsultaRepositoryImpl {

    @PersistenceContext
    private EntityManager manager;

    //ao inves de passar o jpql fixo, podemos montar o jpql dinamicamente
    //dependendo dos parametros que vierem nulos ou nao

    //public List<Entidade> NomeQuaquer(String nome) {

    //    var jpql = new StringBuilder();
    //    jpql.append("and Entidade where 0 = 0 ");

    //    var parametros = new HashMap<String, Object>();                               //mapa para guardar os parametros dinamicos 
    //
    //    if (SpringUtils.hasLength(nome)) {                                            //SpringUtils.hasLength(nome);  verifica se a string nao é nula nem vazia
    //        jpql.append("and nome LIKE %:nome%");
    //        parametros.put("nome", nome);                                             //adiciona o parametro no mapa
    //    }

    //   ...outros parametros
    
    //    TypedQuery<Entidade> query = manager                                          //TypedQuery é uma query que retorna um tipo especifico
    //          .createQuery(jpql.toString(), Entidade.class)
    //
    //    parametros.forEach((chave, valor) -> query.setParameter(chave, valor));      //adiciona os parametros na query
    //    return query.getResultList();
    //}
}
