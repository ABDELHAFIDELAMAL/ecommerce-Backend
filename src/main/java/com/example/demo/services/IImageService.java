package com.example.demo.services;

import com.example.demo.entities.Image;
import com.example.demo.exceptions.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    List<Image> getAllImages();
    Image getImageById(Long imageId) throws NotFoundException;
    Image uploadImage(MultipartFile file , Long productId , String altText) throws IOException, NotFoundException;
    void deleteImage(Long imageId) throws NotFoundException;
    Image getImagesByProduct(Long productId);
}
