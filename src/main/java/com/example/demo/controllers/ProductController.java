package com.example.demo.controllers;

import com.example.demo.entities.Image;
import com.example.demo.entities.Product;
import com.example.demo.entities.Review;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private IProductService productService ;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            var data = productService.getAllProducts();
            return ResponseEntity.ok(new ApiResponse("Products fetched successfully", data , true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody Product product)  {
        try{
            var data = productService.createProduct(product);
            return ResponseEntity.ok(new ApiResponse("Product created successfully" , data , true ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId)  {
        try{
            var data = productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Product fetched successfully" , data , true ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }

    @PutMapping(path = "/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId,@RequestBody Product product)  {
        try{
            var data = productService.updateProduct(productId , product);
            return ResponseEntity.ok(new ApiResponse("Product updated successfully" , data , true ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }

    @DeleteMapping(path = "/delete/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(Long productId) {
        try{
            productService.deleteProduct(productId);
            return ResponseEntity.ok(new ApiResponse("Product deleted successfully" , null , true ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }

    @GetMapping(path = "/search")
    public ResponseEntity<ApiResponse> searchProducts(@RequestParam String text) {
        try{
            var data = productService.searchProducts(text);
            return ResponseEntity.ok(new ApiResponse("Products searched successfully" , data , true ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }

    @GetMapping(path = "/by/category/{categoryId}")
    public ResponseEntity<ApiResponse> getProductsByCategory(@PathVariable Long categoryId) {
        try{
            var data = productService.getProductsByCategory(categoryId);
            return ResponseEntity.ok(new ApiResponse("Products fetched successfully" , data , true ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }

    @GetMapping(path = "/available")
    public ResponseEntity<ApiResponse> getAvailableProducts() {
        try{
            var data = productService.getAvailableProducts();
            return ResponseEntity.ok(new ApiResponse("Available Products fetched successfully" , data , true ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }

    @GetMapping(path = "/top/rating")
    public ResponseEntity<ApiResponse> getTopRatedProducts() {
        try{
            var data = productService.getTopRatedProducts();
            return ResponseEntity.ok(new ApiResponse("Products Top Rating fetched successfully" , data , true ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }

   @PutMapping(path = "/update/quantity")
    public ResponseEntity<ApiResponse> updateQuantity(Long productId, Integer quantity) throws NotFoundException {
       try{
           productService.updateQuantity(productId , quantity);
           return ResponseEntity.ok(new ApiResponse("Update product quantity successfully" , null , true ));
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ApiResponse(e.getMessage(), null , false));
       }
    }

    @PostMapping(path = "/add/image/{productId}")
    public ResponseEntity<ApiResponse> addProductImage(Long productId, Image image) throws NotFoundException {
        try{
            var data = productService.addProductImage(productId , image);
            return ResponseEntity.ok(new ApiResponse("Image added to product successfully" , data , true ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }


    @DeleteMapping(path = "/{productId}/delete/image/{imageId}")
    public ResponseEntity<ApiResponse> removeProductImage(Long productId, Long imageId) throws NotFoundException {
        try{
            var data = productService.removeProductImage(productId , imageId);
            return ResponseEntity.ok(new ApiResponse("Image Product deleted successfully" , data , true ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }

    @GetMapping(path = "/reviews/{productId}")
    public ResponseEntity<ApiResponse> getProductReviews(Long productId) throws NotFoundException {
        try{
            var data = productService.getProductReviews(productId );
            return ResponseEntity.ok(new ApiResponse("Product reviews fetched successfully" , data , true ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }

}
