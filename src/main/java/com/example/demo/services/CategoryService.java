package com.example.demo.services;

import com.example.demo.entities.Category;
import com.example.demo.exceptions.AlReadyExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository ;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) throws AlReadyExistException {
        for(Category category1 : categoryRepository.findAll()){
            if(category.getName().equals(category1.getName())){
                throw new AlReadyExistException("Category Already exist");
            }
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long categoryId) throws NotFoundException {
        return categoryRepository.findById(categoryId)
                .orElseThrow(()-> new NotFoundException("Category not found with id " + categoryId));
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) throws NotFoundException, AlReadyExistException {
        Category category1 = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new NotFoundException("Category not found with id " + categoryId));

        category1.setName(category.getName());
        category1.setDescription(category.getDescription());
        category1.setProducts(category.getProducts());
        for(Category category2 : categoryRepository.findAll()){
            if(category2.getName().equals(category1.getName())){
                throw new AlReadyExistException("Category Already exist");
            }
        }
        categoryRepository.save(category1);
        return category1 ;
    }

    @Override
    public void deleteCategory(Long categoryId) throws NotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new NotFoundException("Category not found with id " + categoryId));
        if(category.getProducts().isEmpty()){
            categoryRepository.deleteById(categoryId);
        }else{
            throw new RuntimeException("You cant delete category not empty !!");
        }
    }

    @Override
    public List<Category> searchCategories(String text) {
        List<Category> resultsCategories = new ArrayList<>();

        for(Category category : categoryRepository.findAll()){
            if(category.getName().toLowerCase().contains(text.toLowerCase()) ||
                    category.getDescription().toLowerCase().contains(text.toLowerCase())
            ){
               resultsCategories.add(category);
            }
        }
        return resultsCategories ;
    }
}
