package com.example.demo.services;

import com.example.demo.entities.OrderItem;

import java.util.List;

public interface IOrderItemService {
    List<OrderItem> getItemsByOrderId(Long orderId);
}
