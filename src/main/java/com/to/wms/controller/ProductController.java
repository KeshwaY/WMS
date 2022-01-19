package com.to.wms.controller;


import com.to.wms.model.Address;
import com.to.wms.model.Product;
import com.to.wms.repository.ProductRepository;
import com.to.wms.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllProducts() {
        List<?> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/name")
    public ResponseEntity<Product> getProductByName(@RequestParam String name) {
        Product product = productService.getProductByName(name);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody @Valid Product product, @RequestParam String categoryName,
                                              @RequestParam String shelf) {

        productService.addProduct(categoryName, shelf, product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/category/{productName}")
    public ResponseEntity<Void> updateProductCategory(@RequestParam String category, @PathVariable String productName) {
        productService.updateProductCategory(category, productName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/quantity/{productName}")
    public ResponseEntity<Void> editProductQuantity(@RequestParam Integer quantity, @PathVariable String productName) {
        productService.editProductQuantity(productName, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{productName}")
    public ResponseEntity<Void> editProduct(@RequestBody Product productToUpdate, @PathVariable String productName) {
        productService.editProduct(productName, productToUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
