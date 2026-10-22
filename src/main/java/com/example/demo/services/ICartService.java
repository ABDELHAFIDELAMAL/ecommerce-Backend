package com.example.demo.services;

import com.example.demo.entities.Cart;

import java.math.BigDecimal;
import java.util.List;

public interface ICartService {
    List<Cart> getAllCarts();
    Cart createCart(Cart cart);
    Cart getCartById(Long cartId);
    Cart getCartByUserId(Long userId);
    BigDecimal getCartTotal(Long cartId);
    void clearCart(Long cartId);
    Cart getOrCreateCart(Long userId);
}
