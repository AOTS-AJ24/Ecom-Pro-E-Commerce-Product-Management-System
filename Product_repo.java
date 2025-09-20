package com.MyShop.Ecom_Pro.repo;

import com.MyShop.Ecom_Pro.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Product_repo extends JpaRepository <Product, Integer>{

    @Query(" select p from Product p where " +
            "LOWER (p.Name) LIKE LOWER (CONCAT('%',:keyword ,'%'))  " +
            " OR LOWER (p.Description) LIKE LOWER (CONCAT('%',:keyword ,'%'))  " +
            "OR LOWER (p.Brand) LIKE LOWER (CONCAT('%',:keyword ,'%'))  " +
            " OR LOWER (p.Category) LIKE LOWER (CONCAT('%',:keyword ,'%')) ")
    List<Product>searchProducts(String keyword);


}


//------------------------------------------------------------------------//
/*
use this if u want to use h2 hibernate steps

1. paste this below code in application .properties
2.remove/ comment out the  /sql connector & jdbc drive connector in pom.xml
3.de-comment the dependency for h2 hibernate

 */
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

// in application properties , thoda vela sathi baher kadhlay
//#spring.application.name=Ecom-Pro
//#
//        #spring.datasource.url=jdbc:h2:mem:MyShop
//#spring.datasource.driverClassName=org.h2.Driver
//#
//        #spring.datasource.username=Abhishek
//#
//        #spring.jpa.show-sql=true
//        #spring.jpa.hibernate.ddl-auto=update
//##spring.jpa.defer-datasource-initialization=true
