package com.example.demo.services;

import com.example.demo.entities.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService implements IOrderItemService{

    @Override
    public OrderItem createOrderItem(Long orderId, Long productId, int quantity) {
        return null;
    }

    @Override
    public OrderItem getOrderItemById(Long id) {
        return null;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return List.of();
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return List.of();
    }

    @Override
    public OrderItem getOrderItemByOrderAndProduct(Long orderId, Long productId) {
        return null;
    }

    @Override
    public OrderItem updateOrderItemQuantity(Long id, int quantity) {
        return null;
    }

    @Override
    public void deleteOrderItem(Long id) {

    }

    @Override
    public void deleteOrderItemsByOrderId(Long orderId) {

    }
}
