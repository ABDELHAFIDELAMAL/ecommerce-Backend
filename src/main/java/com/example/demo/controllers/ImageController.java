package com.example.demo.controllers;

import com.example.demo.entities.Image;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/images")
@CrossOrigin("*")
public class ImageController {

    @Autowired
    private IImageService imageService ;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllImages() {
        try {
            var data = imageService.getAllImages();
            return ResponseEntity.ok(
                    new ApiResponse("Image fetched successfully", data, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<ApiResponse> uploadImage(@RequestParam Long productId , @RequestParam("file") MultipartFile file , @RequestParam(required = false) String altText) {
        try {
            var data = imageService.uploadImage(file , productId , altText);
            return ResponseEntity.ok(
                    new ApiResponse("Image uploaded successfully", data, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }


    @GetMapping("/{imageId}")
    public ResponseEntity<ApiResponse> getImageById(@PathVariable Long imageId) {
        try {
            var data = imageService.getImageById(imageId);
            return ResponseEntity.ok(
                    new ApiResponse("Image fetched successfully", data, true)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null, false));
        }
    }
}
