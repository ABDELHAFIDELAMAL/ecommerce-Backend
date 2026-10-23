package com.example.demo.services;

import com.example.demo.entities.Cart;
import com.example.demo.entities.User;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.UserRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService implements ICartService{

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart createCart(Long userId) throws NotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("User not found"));
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCreatedAt(LocalDateTime.now());
        cart.setTotalPrice(0.0);
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(Long cartId) throws NotFoundException {
        return cartRepository.findById(cartId)
                .orElseThrow(()-> new NotFoundException("Cart not found"));
    }

    @Override
    public Cart getCartByUserId(Long userId) throws NotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("User not found"));
        return user.getCart();
    }

    @Override
    public Double getCartTotal(Long cartId) throws NotFoundException {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(()-> new NotFoundException("Cart not found"));
        return cart.getTotalPrice();
    }

    @Override
    public void clearCart(Long cartId) throws NotFoundException {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(()-> new NotFoundException("Cart not found"));
        cart.getCartItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);
    }

    @Override
    public Cart getOrCreateCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(()->{
                    try {
                        User user = userRepository.findById(userId)
                                .orElseThrow(()-> new NotFoundException("User not found"));
                        Cart cart = user.getCart();
                        cart.setUser(user);
                        cart.setCreatedAt(LocalDateTime.now());
                        cart.setTotalPrice(0.0);
                        return cartRepository.save(cart);
                    } catch (NotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
