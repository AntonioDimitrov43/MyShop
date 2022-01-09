package com.myshop.product.service;

import com.myshop.product.model.Product;

import java.util.Optional;

public interface ProductService {

    Optional<Product> getProduct(Long id);

    Product saveProduct(Product product);

    void deleteProduct(Long id);

    void removeQuantity(int quantity , Long id);
}
