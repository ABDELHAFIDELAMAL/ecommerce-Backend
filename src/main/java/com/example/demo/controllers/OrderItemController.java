package com.example.demo.controllers;

import com.example.demo.entities.OrderItem;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderItems")
@CrossOrigin("*")
public class OrderItemController {

    @Autowired
    private IOrderItemService orderItemService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createOrderItem(@RequestParam Long orderId,@RequestParam Long productId,@RequestParam int quantity) {
        try {
            OrderItem orderItem = orderItemService.createOrderItem(orderId,productId,quantity);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Order item created successfully",orderItem,true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null,false));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllOrderItems() {
        try {
            List<OrderItem> orderItems = orderItemService.getAllOrderItems();
            return ResponseEntity.ok(new ApiResponse("Order items retrieved successfully",orderItems,true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null,false));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOrderItemById(@PathVariable Long id) {
        try {
            OrderItem orderItem = orderItemService.getOrderItemById(id);
            return ResponseEntity.ok(new ApiResponse("Order item retrieved successfully",orderItem,true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null,false));
        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse> getOrderItemsByOrderId(@PathVariable Long orderId) {
        try {
            List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
            return ResponseEntity.ok(new ApiResponse("Order items retrieved successfully",orderItems,true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null,false));
        }
    }

    @GetMapping("/order/{orderId}/product/{productId}")
    public ResponseEntity<ApiResponse> getOrderItemByOrderAndProduct(@PathVariable Long orderId,@PathVariable Long productId) {
        try {
            OrderItem orderItem = orderItemService.getOrderItemByOrderAndProduct(orderId,productId);
            return ResponseEntity.ok(new ApiResponse("Order item retrieved successfully",orderItem,true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null,false));
        }
    }

    @PutMapping("/{id}/quantity")
    public ResponseEntity<ApiResponse> updateOrderItemQuantity(@PathVariable Long id,@RequestParam int quantity) {
        try {
            OrderItem orderItem = orderItemService.updateOrderItemQuantity(id,quantity);
            return ResponseEntity.ok(new ApiResponse("Order item quantity updated successfully",orderItem,true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null,false));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteOrderItem(@PathVariable Long id) {
        try {
            orderItemService.deleteOrderItem(id);
            return ResponseEntity.ok(new ApiResponse("Order item deleted successfully",null,true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null,false));
        }
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse> deleteOrderItemsByOrderId(@PathVariable Long orderId) {
        try {
            orderItemService.deleteOrderItemsByOrderId(orderId);
            return ResponseEntity.ok(new ApiResponse("Order items deleted successfully",null,true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null,false));
        }
    }
}
