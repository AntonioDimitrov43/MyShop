package com.myshop.product.service;

import com.myshop.exceptions.BadDeleteRequestException;
import com.myshop.exceptions.BadIdException;
import com.myshop.exceptions.ElementNotFoundException;
import com.myshop.product.model.Product;
import com.myshop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Optional<Product> getProduct(Long id) {
        if (id == null || id < 0){
            throw new BadIdException("Bad arguments for id");
        }

        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        if (product == null){
            throw new IllegalArgumentException("Products cannot be null during SAVE!");
        }

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id)
    {
        if (id == null || id < 0){
            throw new BadIdException("Bad arguments for id");
        }

        try{
            productRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new BadDeleteRequestException("Product with id:" + id + " does not exist in the repository!");
        }
    }

    @Override
    public void removeQuantity(int quantity, Long productCode) {

        if (productCode == null || productCode < 0){
            throw new IllegalArgumentException("Id cannot be null or negative!");
        }

        try {
           Product product = productRepository.getById(productCode);
            if (product.getQuantity() < quantity){
                throw new IllegalArgumentException("No enough quantity!");
            }

            product.setQuantity(product.getQuantity() - quantity);

            productRepository.save(product);
        }catch (EntityNotFoundException e){
            throw new ElementNotFoundException("Product with id = " + productCode + " not exist!");
        }
    }
}
