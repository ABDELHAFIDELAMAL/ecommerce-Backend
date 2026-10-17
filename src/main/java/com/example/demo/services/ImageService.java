package com.example.demo.services;

import com.example.demo.entities.Image;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService implements IImageService{

    @Autowired
    ImageRepository imageRepository ;

    @Override
    public Image getImageById(Long imageId) throws NotFoundException {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new NotFoundException("Image not exist with id : " + imageId));
    }

    @Override
    public void saveImage() {

    }

    @Override
    public void deleteImage(Long imageId) throws NotFoundException {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new NotFoundException("Image not exist with id : " + imageId));
        imageRepository.deleteById(imageId);
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) throws NotFoundException, IOException {
        Image image = getImageById(imageId);

        if(file.getSize() >= 5){
            throw new IOException("Image size > 5 MB");
        }
        image.setUrl(file.getOriginalFilename());
        image.setAltText(file.getContentType());
    }
}
