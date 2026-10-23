package com.example.demo.controllers;

import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/cartItems")
@CrossOrigin("*")
public class CartItemController {

    @Autowired
    private ICartItemService cartItemService;

    @PostMapping("/addToCart")
    public ResponseEntity<ApiResponse> addToCart(@RequestParam Long cartId, @RequestParam Long productId, @RequestParam int quantity) {
        try {
            Cart cart = cartItemService.addToCart(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Product added to cart successfully", cart, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCartItems() {
        try {
            List<CartItem> cartItems = cartItemService.getAllCartItems();
            return ResponseEntity.ok(new ApiResponse("Cart items retrieved successfully", cartItems, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> getCartItemById(@PathVariable Long cartItemId) {
        try {
            CartItem cartItem = cartItemService.getCartItemById(cartItemId);
            return ResponseEntity.ok(new ApiResponse("Cart item retrieved successfully", cartItem, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<ApiResponse> getCartItemsByCartId(@PathVariable Long cartId) {
        try {
            List<CartItem> cartItems = cartItemService.getCartItemsByCartId(cartId);
            return ResponseEntity.ok(new ApiResponse("Cart items retrieved successfully", cartItems, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable Long cartItemId, @RequestBody CartItem cartItem) {
        try {
            CartItem updatedCartItem = cartItemService.updateCartItem(cartItemId, cartItem);
            return ResponseEntity.ok(new ApiResponse("Cart item updated successfully", updatedCartItem, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @DeleteMapping("/clear/{cartId}")
    public ResponseEntity<ApiResponse> clearCartItems(@PathVariable Long cartId) {
        try {
            cartItemService.clearCartItems(cartId);
            return ResponseEntity.ok(new ApiResponse("Cart cleared successfully", null, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId) {
        try {
            cartItemService.deleteCartItem(cartItemId);
            return ResponseEntity.ok(new ApiResponse("Cart item deleted successfully", null, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping("/cart/{cartId}/product/{productId}")
    public ResponseEntity<ApiResponse> getCartItemByCartAndProduct(@PathVariable Long cartId, @PathVariable Long productId) {
        try {
            Cart cart = cartItemService.getCartItemByCartAndProduct(cartId, productId);
            return ResponseEntity.ok(new ApiResponse("Cart item retrieved successfully", cart, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null, false));
        }
    }
}