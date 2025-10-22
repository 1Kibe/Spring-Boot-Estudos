package com.ryan.Conceitos.JPAEntityManagerAnotation;

//import jakarta.transaction.Transactional;

//import java.util.List;
//
//import org.springframework.stereotype.Component;
//
//import com.ryan.food_delivery_api.domain.Cozinha;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;


//Consultas com JPQL para interagir com o banco de dados
//@Component
public class Code {

    //@PersistenceContext //Proprio do JPA
    //private EntityManager manager;

    //public List<Cozinha> listar(){
    //    //cria a query
    //    //TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class);
    //    //executa a query e retorna a lista
    //   return manager.createQuery("from cozinha", Cozinha.class).getResultList();
    //}
    
    //public Cozinha buscar(Long.id){
    //    return manager.find(Cozinha.class, id);
    //}

    //public List<Cozinha> buscarPorNome(String nome){
    //    return manager.createQuery("from Cozinha where nome like : nome", Cozinha.class)
    //    .setParameter("nome", "%" + nome + "%")
    //    .getResultList();
    //}

    //@Transactional
    //public Cozinha salvar(Cosinha cozinha){
    //    return manager.merge(cozinha);
    //}

    //update
    //Cozinha cozinha = new Cozinha();
    //cozinha.setId(1L);
    //cozinha.setNome("Brasileira");

    //public void remover(Cozinha cozinha){
    //    cozinha = buscar(cozinha.getId());
    //    manager.remove(cozinha);
    //}
    

}
