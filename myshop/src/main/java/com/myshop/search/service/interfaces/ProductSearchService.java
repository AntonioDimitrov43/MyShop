package com.myshop.search.service.interfaces;

import com.myshop.product.model.Product;
import com.myshop.search.criteria.ProductSearchCriteria;

import java.util.List;

public interface ProductSearchService {

    /**
     * Find products for given product search criteria if criteria is not null and at least one parameter is not null.
     * If only name is not null search by name, is only description is not null search by description containing
     * if both are not null search by name containing and description containing
     * @param criteria ProductSearchCriteria for search
     */
    List<Product> findByCriteria(ProductSearchCriteria criteria);
}
