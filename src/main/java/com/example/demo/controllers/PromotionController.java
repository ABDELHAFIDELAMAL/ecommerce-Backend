
package com.example.demo.controllers;

import com.example.demo.entities.Promotion;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/promotions")
@CrossOrigin("*")
public class PromotionController {

    @Autowired
    private IPromotionService promotionService;

    @PostMapping(path = "/create")
    public ResponseEntity<ApiResponse> createPromotion(@RequestBody Promotion promotion) {
        try {
            var data = promotionService.createPromotion(promotion);
            return ResponseEntity.ok(
                    new ApiResponse("Promotion created successfully", data, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllPromotions() {
        try {
            List<Promotion> data = promotionService.getAllPromotions();
            return ResponseEntity.ok(
                    new ApiResponse("Promotions retrieved successfully", data, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping(path = "/{promotionId}")
    public ResponseEntity<ApiResponse> getPromotionById(@PathVariable Long promotionId) {
        try {
            var data = promotionService.getPromotionById(promotionId);
            return ResponseEntity.ok(
                    new ApiResponse("Promotion retrieved successfully", data, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @GetMapping(path = "/active")
    public ResponseEntity<ApiResponse> getPromotionsActive() {
        try {
            List<Promotion> data = promotionService.getPromotionsActive();
            return ResponseEntity.ok(
                    new ApiResponse("Active promotions retrieved successfully", data, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @PutMapping(path = "/update/{promotionId}")
    public ResponseEntity<ApiResponse> updatePromotion(@PathVariable Long promotionId, @RequestBody Promotion promotion) {
        try {
            var data = promotionService.updatePromotion(promotionId, promotion);
            return ResponseEntity.ok(
                    new ApiResponse("Promotion updated successfully", data, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @DeleteMapping(path = "/delete/{promotionId}")
    public ResponseEntity<ApiResponse> deletePromotion(@PathVariable Long promotionId) {
        try {
            promotionService.deletePromotion(promotionId);
            return ResponseEntity.ok(
                    new ApiResponse("Promotion deleted successfully", null, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }
}
