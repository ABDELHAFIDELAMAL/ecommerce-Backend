package com.example.demo.services;

import com.example.demo.entities.Cart;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService implements ICartService{
    @Override
    public List<Cart> getAllCarts() {
        return List.of();
    }

    @Override
    public Cart createCart(Cart cart) {
        return null;
    }

    @Override
    public Cart getCartById(Long cartId) {
        return null;
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return null;
    }

    @Override
    public BigDecimal getCartTotal(Long cartId) {
        return null;
    }

    @Override
    public void clearCart(Long cartId) {

    }

    @Override
    public Cart getOrCreateCart(Long userId) {
        return null;
    }
}
