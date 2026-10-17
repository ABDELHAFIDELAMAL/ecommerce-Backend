package com.example.demo.controllers;

import com.example.demo.entities.Category;
import com.example.demo.exceptions.AlReadyExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/categories")
public class CategoryController{

    @Autowired
    private ICategoryService categoryService ;


    @GetMapping
    public ResponseEntity<ApiResponse> getAllCategories() {
        try{
            var data = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Categories fetched successfully" , data , true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) throws AlReadyExistException {
        try{
            var data = categoryService.createCategory(category);
            return ResponseEntity.ok(new ApiResponse("Category created successfully" , data , true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }

    @GetMapping(path = "/{categoryId}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long categoryId) throws NotFoundException {
        try{
            var data = categoryService.getCategoryById(categoryId);
            return ResponseEntity.ok(new ApiResponse("Category fetched successfully" , data , true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }

    @PutMapping(path = "/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long categoryId,@RequestBody Category category) throws NotFoundException, AlReadyExistException {
        try{
            var data = categoryService.updateCategory(categoryId , category);
            return ResponseEntity.ok(new ApiResponse("Category updated successfully" , data , true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }

    @DeleteMapping(path = "/delete/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) throws NotFoundException {
        try{
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok(new ApiResponse("Category deleted successfully" , null , true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }

    @GetMapping(path = "/search")
    public ResponseEntity<ApiResponse> searchCategories(@RequestParam String text) {
        try{
            var data = categoryService.searchCategories(text);
            return ResponseEntity.ok(new ApiResponse("Categories fetched successfully" , data , true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }
}
