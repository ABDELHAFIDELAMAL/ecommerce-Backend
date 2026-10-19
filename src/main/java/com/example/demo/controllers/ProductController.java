package com.example.demo.controllers;

import com.example.demo.entities.Image;
import com.example.demo.entities.Product;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            var data = productService.getAllProducts();
            return ResponseEntity.ok(
                    new ApiResponse("Products fetched successfully", data, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody Product product) {
        try {
            var data = productService.createProduct(product);
            return ResponseEntity.ok(
                    new ApiResponse("Product created successfully", data, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            var data = productService.getProductById(productId);
            return ResponseEntity.ok(
                    new ApiResponse("Product fetched successfully", data, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(
            @PathVariable Long productId,
            @RequestBody Product product
    ) {
        try {
            var data = productService.updateProduct(productId, product);
            return ResponseEntity.ok(
                    new ApiResponse("Product updated successfully", data, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok(
                    new ApiResponse("Product deleted successfully", null, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchProducts(
            @RequestParam String text
    ) {
        try {
            var data = productService.searchProducts(text);
            return ResponseEntity.ok(
                    new ApiResponse("Products searched successfully", data, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping("/by/category/{categoryId}")
    public ResponseEntity<ApiResponse> getProductsByCategory(@PathVariable Long categoryId) {
        try {
            var data = productService.getProductsByCategory(categoryId);
            return ResponseEntity.ok(
                    new ApiResponse("Products fetched successfully", data, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping("/available")
    public ResponseEntity<ApiResponse> getAvailableProducts() {
        try {
            var data = productService.getAvailableProducts();
            return ResponseEntity.ok(
                    new ApiResponse("Available Products fetched successfully", data, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping("/top/rating")
    public ResponseEntity<ApiResponse> getTopRatedProducts() {
        try {
            var data = productService.getTopRatedProducts();
            return ResponseEntity.ok(
                    new ApiResponse("Products Top Rating fetched successfully", data, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @PutMapping("/update/quantity/{productId}")
    public ResponseEntity<ApiResponse> updateQuantity(@PathVariable Long productId, @RequestParam Integer quantity) {
        try {
            productService.updateQuantity(productId, quantity);
            return ResponseEntity.ok(
                    new ApiResponse("Product quantity updated successfully", null, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }


    @GetMapping("/reviews/{productId}")
    public ResponseEntity<ApiResponse> getProductReviews(@PathVariable Long productId) {
        try {
            var data = productService.getProductReviews(productId);
            return ResponseEntity.ok(
                    new ApiResponse("Product reviews fetched successfully", data, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }
}