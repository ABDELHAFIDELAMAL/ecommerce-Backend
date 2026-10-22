package com.example.demo.services;

import com.example.demo.entities.Payment;

import java.util.List;

public interface IPaymentService {
    Payment createPayment(Long orderId);
    Payment getPaymentById(Long id);
    List<Payment> getAllPayments();
    Payment getPaymentByOrderId(Long orderId);
    List<Payment> getPaymentsByStatus(String status);
    Payment updatePaymentStatus(Long id, String status);
    void deletePayment(Long id);
}