package com.myshop.search.controller;

import com.myshop.product.model.Product;
import com.myshop.search.criteria.ProductSearchCriteria;
import com.myshop.search.service.interfaces.ProductSearchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Log4j2
@RestController
@RequestMapping(path = "/products/search")
public class SearchController {

    private final ProductSearchService productSearchService;

    @Autowired
    public SearchController(ProductSearchService productSearchService) {
        this.productSearchService = productSearchService;
    }

    /**
     * Finds all products which match criteria
     * @param criteria Criteria from JSON request body
     * @return all products found by criteria
     */
    @GetMapping
    public List<Product> findByCriteria(@RequestBody ProductSearchCriteria criteria){

        log.debug("return found products with self relation link");
        return productSearchService.findByCriteria(criteria);
    }
}
