package com.example.demo.services;

import com.example.demo.entities.Order;
import com.example.demo.enums.OrderStatus;
import com.example.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Long userId) {
        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return List.of();
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return List.of();
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return List.of();
    }

    @Override
    public Order updateOrderStatus(Long id, OrderStatus status) {
        return null;
    }

    @Override
    public Order cancelOrder(Long id) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {

    }

    @Override
    public Double getOrderTotal(Long orderId) {
        return 0.0;
    }
}