package com.example.demo.services;

import com.example.demo.entities.Cart;
import com.example.demo.exceptions.NotFoundException;

import java.math.BigDecimal;
import java.util.List;

public interface ICartService {
    List<Cart> getAllCarts();
    Cart createCart(Long userId) throws NotFoundException;
    Cart getCartById(Long cartId) throws NotFoundException;
    Cart getCartByUserId(Long userId) throws NotFoundException;
    Double getCartTotal(Long cartId) throws NotFoundException;
    void clearCart(Long cartId) throws NotFoundException;
    Cart getOrCreateCart(Long userId);
}
