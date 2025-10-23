package com.ryan.Conceitos.QueryMethods.SpecificationsSDJ;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ryan.food_delivery_api.domain.Restaurante;

//extenda JpaSpecificationExecutor para usar Specifications
public interface CodeRepository extends JpaSpecificationExecutor<Restaurante> {

}
