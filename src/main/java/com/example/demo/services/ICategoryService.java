package com.example.demo.services;

import com.example.demo.entities.Category;
import com.example.demo.entities.Product;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategories();
    Category createCategory(Category category);
    Category getCategoryById(Long categoryId);
    Category updateCategory(Long categoryId , Category category);
    void deleteCategory(Long categoryId);
    List<Category> searchCategories(String text);
}
