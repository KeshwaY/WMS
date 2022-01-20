package com.to.wms.repository;

import com.to.wms.model.Product;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findProductByName(String productName);

    @Aggregation(pipeline = {
            "{$lookup: {from: 'location', localField: 'location', foreignField: '_id', as: 'locationDoc'}}, " +
                    "{$match: {'locationDoc.shelf': '?0'}}, {$project: {locationDoc: 0}}"
    })
    List<Product> findAllProductsByLocationShelf(String shelf);

    @Aggregation(pipeline = {
            "{$lookup: {from: 'category', localField: 'category', foreignField: '_id', as: 'categoryDoc'}}, " +
                    "{$match: {'categoryDoc.name': '?0'}}, {$project: {categoryDoc: 0}}"
    })
    List<Product> findAllProductsByCategoryName(String categoryName);

    /**
     *
     * @param productName name of product
     * @return value which specifies number of products by name
     */
    Integer countByName(String productName);

}
