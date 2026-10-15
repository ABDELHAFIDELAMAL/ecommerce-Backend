package com.example.demo.services;

import com.example.demo.entities.Order;
import com.example.demo.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public interface IOrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
    Order getOrderById(Long orderId);
    List<Order> getMyOrders();
    Order updateOrderStatus(Long orderId);
    void cancelOrder(Long orderId);
    BigDecimal calculateOrderTotal(Long oderId);
    List<Order> getOrdersByStatus(OrderStatus orderStatus);
    List<Order> getOrdersByUser(Long userId);
}
