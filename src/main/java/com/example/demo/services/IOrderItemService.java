package com.example.demo.services;

import com.example.demo.entities.OrderItem;

import java.util.List;

public interface IOrderItemService {
    OrderItem createOrderItem(Long orderId, Long productId, int quantity);
    OrderItem getOrderItemById(Long id);
    List<OrderItem> getAllOrderItems();
    List<OrderItem> getOrderItemsByOrderId(Long orderId);
    OrderItem getOrderItemByOrderAndProduct(Long orderId, Long productId);
    OrderItem updateOrderItemQuantity(Long id, int quantity);
    void deleteOrderItem(Long id);
    void deleteOrderItemsByOrderId(Long orderId);
}
