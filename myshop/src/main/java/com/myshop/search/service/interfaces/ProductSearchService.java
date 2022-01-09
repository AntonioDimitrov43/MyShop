package com.myshop.search.service.interfaces;

import com.myshop.product.model.Product;
import com.myshop.search.criteria.ProductSearchCriteria;

import java.util.List;

public interface ProductSearchService {
    List<Product> findByCriteria(ProductSearchCriteria criteria);
}
