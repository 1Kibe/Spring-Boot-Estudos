package com.ryan.Conceitos.QualifierPersonalizado;

//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//
//import org.springframework.beans.factory.annotation.Qualifier;

//@Retention(RetentionPolicy.RUNTIME)
//@Qualifier
public @interface TipoDoNotificador {
    //NivelUrgencia value();
}

//pronto para ser usado em injeção de dependência com Qualifier personalizado
//exemplo de uso: @TipoDoNotificador(NivelUrgencia.Urgente)
//
