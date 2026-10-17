package com.example.demo.services;

import com.example.demo.entities.Order;
import com.example.demo.enums.OrderStatus;
import com.example.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private OrderRepository orderRepository ;
    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<Order> getMyOrders() {
        return List.of();
    }

    @Override
    public Order updateOrderStatus(Long orderId) {
        return null;
    }

    @Override
    public void cancelOrder(Long orderId) {

    }

    @Override
    public BigDecimal calculateOrderTotal(Long oderId) {
        return null;
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus orderStatus) {
        return List.of();
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        return List.of();
    }
}
