package com.example.demo.services;

import com.example.demo.entities.Payment;

import java.util.List;

public interface IPaymentService {

    Payment createPayment(Payment payment);

    List<Payment> getAllPayments();

    Payment getPaymentById(Long paymentId);

    Payment updatePayment(Long paymentId, Payment payment);

    void deletePayment(Long paymentId);
}