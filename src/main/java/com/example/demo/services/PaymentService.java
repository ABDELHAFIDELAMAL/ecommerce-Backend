package com.example.demo.services;

import com.example.demo.entities.Payment;
import com.example.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public Payment createPayment(Long orderId) {
        return null;
    }

    @Override
    public Payment getPaymentById(Long id) {
        return null;
    }

    @Override
    public List<Payment> getAllPayments() {
        return List.of();
    }

    @Override
    public Payment getPaymentByOrderId(Long orderId) {
        return null;
    }

    @Override
    public List<Payment> getPaymentsByStatus(String status) {
        return List.of();
    }

    @Override
    public Payment updatePaymentStatus(Long id, String status) {
        return null;
    }

    @Override
    public void deletePayment(Long id) {

    }
}