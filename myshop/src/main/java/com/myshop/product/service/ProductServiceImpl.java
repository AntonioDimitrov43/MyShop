package com.myshop.product.service;

import com.myshop.security.exceptions.BadDeleteRequestException;
import com.myshop.security.exceptions.BadIdException;
import com.myshop.security.exceptions.ElementNotFoundException;
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


    /**
     * Acquires product according to id.
     * @param id not null and positive id
     * @return found product
     */
    @Override
    public Optional<Product> getProduct(Long id) {
        if (id == null || id < 0){
            throw new BadIdException("Bad arguments for id");
        }

        return productRepository.findById(id);
    }

    /**
     * Saves product to the repo.
     * @param product not null product
     * @return saves product
     */
    @Override
    public Product saveProduct(Product product) {
        if (product == null){
            throw new IllegalArgumentException("Products cannot be null during SAVE!");
        }

        return productRepository.save(product);
    }

    /**
     * Deletes product from the repo by id.
     * @param id not null and positive id
     */
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

    /**
     * Removes a certain quantity of a product from the repo and saves the changes to the product information
     * @param quantity not null and positive quantity
     * @param productCode not null and positive product code
     */
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
