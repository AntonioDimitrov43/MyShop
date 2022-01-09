package com.myshop.search.service;


import com.myshop.product.model.Product;
import com.myshop.product.repository.ProductRepository;
import com.myshop.search.criteria.ProductSearchCriteria;
import com.myshop.search.service.interfaces.ProductSearchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
import java.util.List;

@Log4j2
@Service
public class ProductSearchServiceImpl implements ProductSearchService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductSearchServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Find products for given product search criteria if criteria is not null and at least one parameter is not null.
     * If only name is not null search by name, is only description is not null search by description containing
     * if both are not null search by name containing and description containing
     * @param criteria ProductSearchCriteria for search
     * @return List of products that match given criteria
     * @throws IllegalArgumentException if criteria is null or both parameters are null
     */
    @Lock(LockModeType.READ)
    @Override
    public List<Product> findByCriteria(ProductSearchCriteria criteria) {
        if (criteria == null){
            log.warn("Criteria cannot be null");
            throw new IllegalArgumentException("Criteria cannot be null");
        }

        if (criteria.getName() != null && criteria.getDescription() != null){
            log.debug("return products searched by name = "+ criteria.getName() +
                    " and description = " + criteria.getDescription());
            return productRepository
                    .findByNameContainingAndDescriptionContaining(criteria.getName(), criteria.getDescription());
        }
        if (criteria.getName() != null){
            log.debug("return products searched by name = " + criteria.getName());
            return findProductsByName(criteria.getName());
        }
        if (criteria.getDescription() != null){
            log.debug("return products searched by description  contains = " + criteria.getDescription());
            return productRepository.findByDescriptionContaining(criteria.getDescription());
        }

        log.warn("Criteria must have at least 1 parameter");
        throw new IllegalArgumentException("Criteria must have at least 1 parameter to search!");
    }

    /**
     * Search product by name first search by full name if no products exist by full name, search by name
     * starts with given string, if again no name, search by name containing given string
     * @param name name or peace of name to search
     * @return Products that match given name
     */
    @Lock(LockModeType.READ)
    private List<Product> findProductsByName(String name){
        List<Product> products;
        log.debug("try to find products with all name = " + name);
        products = productRepository.findByName(name);

        if (products.isEmpty()){
            log.debug("try to find products that name starts with = " + name);

            products =  productRepository.findByNameStartingWith(name);
        }
        if (products.isEmpty()){
            log.debug("try to find products with that name contains = " + name);

            return productRepository.findByNameContaining(name);
        }else {
            return products;
        }
    }
}
