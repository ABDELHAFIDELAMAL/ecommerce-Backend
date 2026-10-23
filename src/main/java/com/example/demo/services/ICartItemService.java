package com.example.demo.services;

import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.exceptions.NotFoundException;

import java.util.List;

public interface ICartItemService {
    Cart addToCart(Long cartId , Long productId , int quantity) throws NotFoundException;
    List<CartItem> getAllCartItems();
    CartItem getCartItemById(Long cartItemId);
    List<CartItem> getCartItemsByCartId(Long cartId);
    CartItem updateCartItem(Long cartItemId, CartItem cartItem);
    void clearCartItems(Long cartId);
    void deleteCartItem(Long cartItemId);
    Cart getCartItemByCartAndProduct(Long cartId , Long productId);
}