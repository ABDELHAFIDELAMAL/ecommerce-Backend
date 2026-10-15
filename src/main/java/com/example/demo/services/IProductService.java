package com.example.demo.services;

import com.example.demo.entities.Category;
import com.example.demo.entities.Image;
import com.example.demo.entities.Product;
import com.example.demo.entities.Review;
import com.example.demo.exceptions.AlReadyExistException;
import com.example.demo.exceptions.NotFoundException;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product createProduct(Product product) throws AlReadyExistException;
    Product getProductById(Long productId ) throws NotFoundException;
    Product updateProduct(Long productId , Product product) throws NotFoundException;
    void deleteProduct(Long productId) throws NotFoundException;
    List<Product> searchProducts(String text);
    List<Product> getProductsByCategory(Long categoryId) throws NotFoundException;
    List<Product> getAvailableProducts();
    List<Product> getTopRatedProducts();
    void updateQuantity(Long productId , Integer quantity) throws NotFoundException;
    Product addProductImage(Long productId , Image image) throws NotFoundException;
    Product removeProductImage(Long productId , Long imageId) throws NotFoundException;
    List<Review> getProductReviews(Long productId) throws NotFoundException;
}
