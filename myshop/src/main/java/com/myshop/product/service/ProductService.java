package com.myshop.product.service;

import com.myshop.product.model.Product;

import java.util.Optional;

public interface ProductService {

    /**
     * Acquires product according to id.
     * @param id not null and positive id
     * @return found product
     */
    Optional<Product> getProduct(Long id);

    /**
     * Saves product to the repo.
     * @param product not null product
     * @return saves product
     */
    Product saveProduct(Product product);

    /**
     * Deletes product from the repo by id.
     * @param id not null and positive id
     */
    void deleteProduct(Long id);

    /**
     * Removes a certain quantity of a product from the repo and saves the changes to the product information
     * @param quantity not null and positive quantity
     * @param id not null and positive product code
     */
    void removeQuantity(int quantity , Long id);
}
