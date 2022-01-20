package com.to.wms.controller;

import com.to.wms.model.Category;
import com.to.wms.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/by-name")
    public ResponseEntity<Category> getCategoryByName(@RequestParam String name) {
        Category category = categoryService.getCategoryByName(name);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllCategories() {
        List<?> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addCategory( @RequestBody Category category) {
        categoryService.addCategory(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@Valid @RequestBody Category categoryToUpdate, @PathVariable String id) {
        categoryService.editCategory(id, categoryToUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
