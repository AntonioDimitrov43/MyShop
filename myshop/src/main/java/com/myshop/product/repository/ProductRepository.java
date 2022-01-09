package com.myshop.product.repository;

import com.myshop.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    /**
     * Looks through all products and finds ones which contain the name
     * @param name valid name input
     * @return list of found items with contained name
     */
    List<Product> findByNameContaining(String name);

    /**
     * Looks through all products and finds which ones contain the description
     * @param description valid description input
     * @return list of found items with contained name
     */
    List<Product> findByDescriptionContaining(String description);

    /**
     * Looks through all products and finds which ones contain the description and name
     * @param name valid name input
     * @param description valid description input
     * @return list of found items with contained name and description
     */
    List<Product> findByNameContainingAndDescriptionContaining(String name, String description);

    /**
     * Looks through all products and finds which ones contain the name fully
     * @param name valid name
     * @return list of found items with contained name
     */
    List<Product> findByName(String name);

    /**
     * Looks trough all products and finds which ones start with the name
     * @param name valid name
     * @return list of found items which start with the name
     */
    List<Product> findByNameStartingWith(String name);
}
