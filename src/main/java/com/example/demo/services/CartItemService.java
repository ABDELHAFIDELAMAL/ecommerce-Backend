package com.example.demo.services;

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
    public CartItem createCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "CartItem not found with id: " + cartItemId
                        )
                );
    }

    @Override
    public CartItem updateCartItem(Long cartItemId, CartItem cartItem) {

        CartItem existingCartItem = getCartItemById(cartItemId);

        existingCartItem.setQuantity(cartItem.getQuantity());

        return cartItemRepository.save(existingCartItem);
    }

    @Override
    public void deleteCartItem(Long cartItemId) {

        CartItem cartItem = getCartItemById(cartItemId);

        cartItemRepository.delete(cartItem);
    }
}