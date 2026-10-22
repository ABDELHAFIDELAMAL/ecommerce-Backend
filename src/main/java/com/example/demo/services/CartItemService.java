package com.example.demo.services;

import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartItemService implements ICartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem addToCart(Long cartId, Long productId, int quantity) {
        return null;
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return List.of();
    }

    @Override
    public CartItem getCartItemById(Long cartItemId) {
        return null;
    }

    @Override
    public List<CartItem> getCartItemsByCartId(Long cartId) {
        return List.of();
    }

    @Override
    public CartItem updateCartItem(Long cartItemId, CartItem cartItem) {
        return null;
    }

    @Override
    public void clearCartItems(Long cartId) {

    }

    @Override
    public void deleteCartItem(Long cartItemId) {

    }

    @Override
    public Cart getCartItemByCartAndProduct(Long cartId, Long productId) {
        return null;
    }
}