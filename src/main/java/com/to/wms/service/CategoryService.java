package com.to.wms.service;

import com.to.wms.model.Category;
import com.to.wms.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService extends BasicGenericService<CategoryRepository> {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository genericCategoryRepository, CategoryRepository categoryRepository) {
        super(genericCategoryRepository);
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findCategoryByName(categoryName);
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void editCategory(String categoryId, Category categoryToUpdate) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalStateException("Category not found"));
        category.setName(categoryToUpdate.getName());
        categoryRepository.save(category);
    }
}
