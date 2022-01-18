package com.to.wms.repository;

import com.to.wms.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String > {
    Category findCategoryByName(String categoryName);
}
