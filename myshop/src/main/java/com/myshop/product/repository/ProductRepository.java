package com.myshop.product.repository;

import com.myshop.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findByProductCode(Long productCode);

    Product getByProductCode(Long productCode);
}
