package com.ryan.Conceitos.JpaRepositoryCustomizandoBase;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;
//extenda SimpleJpaRepository, e implemente a sua Custom
public class CustomJpaRepositoryImpl<T, ID>
extends SimpleJpaRepository<T, ID> 
implements CustomJpaRepository<T, ID>{

    private EntityManager manager;

    //gere um superconstrutoe com EntityManager e EntityInformation
    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.manager = entityManager;
    }
    

    //chame os metodos para sobreescrever
    @Override
    public Optional<T> buscarPrimeiro(){
        //esses gets sao metodos de SimpleJpaRepository
        var jpql = "from " + getDomainClass().getName();        //retorna um 'from NOMECLASSE'

        T entity = manager.createQuery(jpql, getDomainClass())
            .setMaxResults(1)
            .getSingleResult();
        return Optional.ofNullable(entity);
    };


    //depois de criado os metodos
    //em AppApplication.java

    /*
     * coloque a anotacao @EnableJpaRepositories abaixo da @SpringBootApplication
     * 
     * exemplo de uso
     * @EnableJpaRepositories(repositoryBaseClass = SeuCustomRepositoryImpl.class) 
     * 
     * //ele vai subistituir o seu SimpleJpaRepository com os seu metodos Customizados
     * 
     * 
     * 
     * //em resource usa-se um retorno normal mas agora podendo usar seu metodoRepositoryCustom
     *  return repository.seuMetodoCustomizado();
     * 
     * //ele fara o que vc declarar aqui, no caso seu Impl
     */
}
