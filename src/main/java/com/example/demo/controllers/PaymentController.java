package com.example.demo.controllers;

import com.example.demo.entities.Payment;
import com.example.demo.enums.PaymentStatus;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping("/create/{orderId}")
    public ResponseEntity<ApiResponse> createPayment(@PathVariable Long orderId) {
        try {
            Payment payment = paymentService.createPayment(orderId);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Payment created successfully",payment,true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null,false));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPaymentById(@PathVariable Long id) {
        try {
            Payment payment = paymentService.getPaymentById(id);
            return ResponseEntity.ok(new ApiResponse("Payment retrieved successfully",payment,true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null,false));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllPayments() {
        try {
            List<Payment> payments = paymentService.getAllPayments();
            return ResponseEntity.ok(new ApiResponse("Payments retrieved successfully",payments,true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null,false));
        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse> getPaymentByOrderId(@PathVariable Long orderId) {
        try {
            Payment payment = paymentService.getPaymentByOrderId(orderId);
            return ResponseEntity.ok(new ApiResponse("Payment retrieved successfully",payment,true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null,false));
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse> getPaymentsByStatus(@PathVariable PaymentStatus status) {
        try {
            List<Payment> payments = paymentService.getPaymentsByStatus(status);
            return ResponseEntity.ok(new ApiResponse("Payments retrieved successfully",payments,true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null,false));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse> updatePaymentStatus(@PathVariable Long id,@RequestParam PaymentStatus status) {
        try {
            Payment payment = paymentService.updatePaymentStatus(id,status);
            return ResponseEntity.ok(new ApiResponse("Payment status updated successfully",payment,true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null,false));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePayment(@PathVariable Long id) {
        try {
            paymentService.deletePayment(id);
            return ResponseEntity.ok(new ApiResponse("Payment deleted successfully",null,true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null,false));
        }
    }
}