package com.myshop.product.controller;

import com.myshop.security.exceptions.BadRequestContentException;
import com.myshop.security.exceptions.ElementNotFoundException;
import com.myshop.product.model.Product;
import com.myshop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * This is Product controller for creating getting a product.
     * @param id Path variable to find the product
     * @return Found product
     */
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable("id") Long id){
        return productService.getProduct(id).orElseThrow(() -> new ElementNotFoundException("Product with id = " + id +
                " not found!"));
    }

    /**
     *  This is Product controller for creating new product.
     * @param product Valid product from JSON request  body
     * @param errors for invalid product JSON request body
     * @return saved Product
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody @Valid Product product, Errors errors){
        if (errors.hasErrors()){
            if (errors.getFieldError() != null) {
                throw new BadRequestContentException(errors.getFieldError().getDefaultMessage());
            } else {
                throw new BadRequestContentException();
            }
        }

        return productService.saveProduct(product);
    }

    /**
     * This is a Product controller for deleting a product
     * @param id Path variable to find the product to delete
     */
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }

}
