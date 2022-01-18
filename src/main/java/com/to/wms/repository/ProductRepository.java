package com.to.wms.repository;

import com.to.wms.model.Location;
import com.to.wms.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findProductByName(String productName);

    Product findProductByLocationShelf(String shelf);

    List<Product> findAllProductsByCategoryName(String categoryName);

    /**
     *
     * @param productName name of product
     * @return value which specifies number of products by name
     */
    Integer countByName(String productName);
}
