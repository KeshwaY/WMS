package com.to.wms.controller;


import com.to.wms.model.Product;
import com.to.wms.repository.ProductRepository;
import com.to.wms.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody Product product, @RequestParam String categoryName,
                                              @RequestParam String shelf) {

        productService.addProduct(categoryName, shelf, product);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
