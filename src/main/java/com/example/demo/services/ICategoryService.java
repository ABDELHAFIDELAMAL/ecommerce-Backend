package com.example.demo.services;

import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.exceptions.AlReadyExistException;
import com.example.demo.exceptions.NotFoundException;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategories();
    Category createCategory(Category category) throws AlReadyExistException;
    Category getCategoryById(Long categoryId) throws NotFoundException;
    Category updateCategory(Long categoryId , Category category) throws NotFoundException, AlReadyExistException;
    void deleteCategory(Long categoryId) throws NotFoundException;
    List<Category> searchCategories(String text);
}
