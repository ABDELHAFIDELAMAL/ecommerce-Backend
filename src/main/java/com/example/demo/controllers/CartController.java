package com.example.demo.controllers;

import com.example.demo.entities.Cart;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCarts() {
        try {
            List<Cart> carts = cartService.getAllCarts();
            return ResponseEntity.ok(new ApiResponse("Carts retrieved successfully", carts, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<ApiResponse> createCart(@PathVariable Long userId) {
        try {
            Cart cart = cartService.createCart(userId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("Cart created successfully", cart, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<ApiResponse> getCartById(@PathVariable Long cartId) {
        try {
            Cart cart = cartService.getCartById(cartId);
            return ResponseEntity.ok(new ApiResponse("Cart retrieved successfully", cart, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getCartByUserId(@PathVariable Long userId) {
        try {
            Cart cart = cartService.getCartByUserId(userId);
            return ResponseEntity.ok(new ApiResponse("Cart retrieved successfully", cart, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping("/{cartId}/total")
    public ResponseEntity<ApiResponse> getCartTotal(@PathVariable Long cartId) {
        try {
            Double total = cartService.getCartTotal(cartId);
            return ResponseEntity.ok(new ApiResponse("Cart total retrieved successfully", total, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId) {
        try {
            cartService.clearCart(cartId);
            return ResponseEntity.ok(new ApiResponse("Cart cleared successfully", null, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @PostMapping("/user/{userId}/get-or-create")
    public ResponseEntity<ApiResponse> getOrCreateCart(@PathVariable Long userId) {
        try {
            Cart cart = cartService.getOrCreateCart(userId);
            return ResponseEntity.ok(new ApiResponse("Cart retrieved or created successfully", cart, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }
}