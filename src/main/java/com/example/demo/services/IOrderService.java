package com.example.demo.services;

import com.example.demo.entities.Order;
import com.example.demo.enums.OrderStatus;
import java.util.List;

public interface IOrderService {
    Order createOrder(Long userId);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
    List<Order> getOrdersByUserId(Long userId);
    List<Order> getOrdersByStatus(OrderStatus status);
    Order updateOrderStatus(Long id, OrderStatus status);
    Order cancelOrder(Long id);
    void deleteOrder(Long id);
    Double getOrderTotal(Long orderId);
}
