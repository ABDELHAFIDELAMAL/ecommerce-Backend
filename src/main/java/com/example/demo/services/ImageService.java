package com.example.demo.services;

import com.example.demo.entities.Image;
import com.example.demo.entities.Product;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.ImageRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService implements IImageService{

    @Autowired
    private ImageRepository imageRepository ;
    @Autowired
    private ProductRepository productRepository ;

    private final Path uploadDirectory = Path.of("uploads").toAbsolutePath().normalize();

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public Image getImageById(Long imageId) throws NotFoundException {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new NotFoundException("Image not exist with id : " + imageId));
    }


    @Override
    public Image uploadImage(MultipartFile file, Long productId, String altText) throws IOException, NotFoundException {

        if(file == null || file.isEmpty()){
            throw new IOException("Image file is required !");
        }


        Product product = productRepository.findById(productId)
                .orElseThrow(()->new NotFoundException("Product not found with Id : " + productId));

        Files.createDirectories(uploadDirectory);
        String originalName = file.getOriginalFilename();
        String extension = "";

        if(originalName != null && originalName.contains(".")){
            extension = originalName.substring(originalName.lastIndexOf(".")).toLowerCase();
        }
        String contentType = file.getContentType();

        if (!contentType.equals("image/jpeg")
                && !contentType.equals("image/png")
                && !contentType.equals("image/webp")
                && !contentType.equals("image/gif")) {
            throw new RuntimeException("Only JPEG , PNG , WEBP and GIF images are allowed");
        }

        if (file.getSize() > 10 * 1024 * 1024) {
            throw new IOException("Image size must be under 10 MB");
        }

        String fileName = UUID.randomUUID() + extension ;
        Path destination = uploadDirectory.resolve(fileName).normalize();

        if(!destination.startsWith(uploadDirectory)){
            throw new IllegalArgumentException("Invalid file path");
        }

        Files.copy(file.getInputStream(),destination);

        Image image = new Image() ;
        image.setFileName(fileName);
        image.setAltText(altText);
        image.setImageUrl("/images/" + fileName);
        image.setProduct(product);

        return imageRepository.save(image);

    }


    @Override
    public void deleteImage(Long imageId) throws NotFoundException {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new NotFoundException("Image not exist with id : " + imageId));
        imageRepository.deleteById(imageId);
    }

    @Override
    public Image getImagesByProduct(Long productId) {
        return (Image) Collections.singletonList(imageRepository.findById(productId).orElseThrow(() -> new RuntimeException("Eror")));
    }


}
