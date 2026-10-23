package com.example.demo.controllers;

import com.example.demo.entities.Address;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.IAddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/addresses")
@CrossOrigin("*")
public class AddressController {
    private final IAddressService addressService;

    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createAddress(@RequestBody Address address) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("Address created successfully", addressService.createAddress(address), true));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllAddresses() {
        try {
            return ResponseEntity.ok(new ApiResponse("Addresses retrieved successfully", addressService.getAllAddresses(), true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAddressById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new ApiResponse("Address retrieved successfully", addressService.getAddressById(id), true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getAddressesByUserId(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(new ApiResponse("User addresses retrieved successfully", addressService.getAddressesByUserId(userId), true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        try {
            return ResponseEntity.ok(new ApiResponse("Address updated successfully", addressService.updateAddress(id, address), true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Long id) {
        try {
            addressService.deleteAddress(id);
            return ResponseEntity.ok(new ApiResponse("Address deleted successfully", null, true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null, false));
        }
    }

}