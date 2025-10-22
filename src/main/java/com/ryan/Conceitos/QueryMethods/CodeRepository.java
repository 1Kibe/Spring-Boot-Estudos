package com.ryan.Conceitos.QueryMethods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends JpaRepository<Entidade, Long>, CodeRepositoryQuery {
    //------------------------------------------------------------------------------------------------

    //Exemlos de query methods
    //prefix + atributo + operador + (e/ou + atributo + operador)

    //List<Entidade> findByNome(String nome);
    //List<Entidade> findByNomeContaining(String nome);
    //List<Entidade> findByNomeLike(String nome);
    //List<Entidade> findByNomeAndOutroAtributo(String nome, Tipo outroAtributo);

    //------------------------------------------------------------------------------------------------

    //Keywords
    //Documentação:https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    //Busca por qualquer parte do nome
    //List<Entidade> findByNomeContaining(String nome);

    //------------------------------------------------------------------------------------------------

    //Prefixos
    //todos esses prefixos funcionam da mesma forma

    //List<Entidade> findByNome(String nome);
    //List<Entidade> queryByNome(String nome);
    //List<Entidade> getByNome(String nome);
    //List<Entidade> readByNome(String nome);
    //List<Entidade> streamByNome(String nome);

    //retorna true ou false se existir o nome
    //boolean existByNome(String nome);

    //conta quantos registros existem 
    //int contByEntidadeId(Integer id);

    //------------------------------------------------------------------------------------------------

    //Flags depois dos prefixos
    // prefix + Flag + parametroDaFlag + By + Atributo + Operador

    //Optional<Entidade> findFirtsByNomeContaining(String nome);
    //List<Entidade> findTop2ByNome(String nome);

    //------------------------------------------------------------------------------------------------

    //Query Customizavel
    // @Query("FROM Entidade WHERE nome LIKE %:nome% and entidade.id = :id")
    // List<Entidade> metodoCustomizavel(@Param("nome") String nome, @Param("id") Long id);

    //------------------------------------------------------------------------------------------------

    //Externalizando Queries em XMl
    //Criar um aruivo em src/main/resources/META-INF/orm.xml

    //exemplo de uso em orm.txt desse packge atual

    //------------------------------------------------------------------------------------------------

    //Classe Repository Impl
    //Explicacao em CodeRepositoryImpl.java

    //public List<Entidade> NomeQuaquer(String nome) 


}  
