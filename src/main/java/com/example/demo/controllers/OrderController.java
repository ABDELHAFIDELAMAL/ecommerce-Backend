package com.example.demo.controllers;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private IOrderService orderService ;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllOrders() throws NotFoundException {
        try{
            var data = orderService.getAllOrders();
            return ResponseEntity.ok(new ApiResponse("Orders fetched successfully" , data , true ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }

}
