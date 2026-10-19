package com.example.demo.services;

import com.example.demo.entities.CartItem;

import java.util.List;

public interface ICartItemService {

    CartItem createCartItem(CartItem cartItem);

    List<CartItem> getAllCartItems();

    CartItem getCartItemById(Long cartItemId);

    CartItem updateCartItem(Long cartItemId, CartItem cartItem);

    void deleteCartItem(Long cartItemId);
}