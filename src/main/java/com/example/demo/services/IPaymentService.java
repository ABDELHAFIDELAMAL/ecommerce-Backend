package com.example.demo.services;

import com.example.demo.entities.Payment;
import com.example.demo.enums.PaymentStatus;

import java.util.List;

public interface IPaymentService {
    Payment createPayment(Long orderId);
    Payment getPaymentById(Long id);
    List<Payment> getAllPayments();
    Payment getPaymentByOrderId(Long orderId);
    List<Payment> getPaymentsByStatus(PaymentStatus status);

    Payment updatePaymentStatus(Long id, PaymentStatus status);
    void deletePayment(Long id);
}