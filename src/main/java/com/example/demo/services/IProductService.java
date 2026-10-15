package com.example.demo.services;

import com.example.demo.entities.Category;
import com.example.demo.entities.Image;
import com.example.demo.entities.Product;
import com.example.demo.entities.Review;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product getProductById(Long productId );
    Product updateProduct(Long productId , Product product) ;
    void deleteProduct(Long productId);
    List<Product> searchProducts(String text);
    List<Product> getProductsByCategory(Category category);
    List<Product> getAvailableProducts();
    List<Product> getTopRatedProducts();
    void updateQuantity(Long productId , Integer quantity);
    Product addProductImage(Long productId , Image image);
    Product removeProductImage(Long productId , Long imageId);
    List<Review> getProductReviews(Long productId);
    List<Review> getProductsByCategory(Long categoryId);
}
