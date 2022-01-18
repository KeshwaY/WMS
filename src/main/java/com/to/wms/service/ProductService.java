package com.to.wms.service;

import com.to.wms.model.Category;
import com.to.wms.model.Location;
import com.to.wms.model.Product;
import com.to.wms.repository.CategoryRepository;
import com.to.wms.repository.LocationRepository;
import com.to.wms.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, LocationRepository locationRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getProductByName(String productName) {
        return productRepository.findProductByName(productName);
    }

    @Transactional(readOnly = true)
    public Product getProductByLocation(String shelf) {
        return productRepository.findProductByLocationShelf(shelf);
    }

    @Transactional(readOnly = true)
    public Product getProductsByCategory(String categoryName) {
        return productRepository.findProductByCategoryName(categoryName);
    }

    public void addProduct(String categoryName, String shelf, Product product) {
        Location location = locationRepository.findLocationByShelf(shelf);
        Category category = categoryRepository.findCategoryByName(categoryName);
        product.setLocation(location);
        product.setCategory(category);
        productRepository.save(product);
    }

    public void addProductToCategory(String categoryName, String productName) {
        Category category = categoryRepository.findCategoryByName(categoryName);
        Product product = productRepository.findProductByName(productName);
        product.setCategory(category);
        productRepository.save(product);
    }

    public void editProductQuantity(String productId, Integer quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("No such product found"));
        product.setQuantity(quantity);
        productRepository.save(product);
    }

    public void editProduct(String productId, Product productToUpdate) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("No such product found"));
        product.setDescription(productToUpdate.getDescription());
        product.setName(productToUpdate.getName());
        product.setQuantity(productToUpdate.getQuantity());
        productRepository.save(product);
    }

    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

}
