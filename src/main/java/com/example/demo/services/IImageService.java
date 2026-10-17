package com.example.demo.services;

import com.example.demo.entities.Image;
import com.example.demo.exceptions.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageService {
    Image getImageById(Long imageId) throws NotFoundException;
    void saveImage();
    void deleteImage(Long imageId) throws NotFoundException;
    void updateImage(MultipartFile file , Long imageId) throws NotFoundException, IOException;
}
