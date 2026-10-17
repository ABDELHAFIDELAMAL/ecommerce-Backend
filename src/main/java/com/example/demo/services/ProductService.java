package com.example.demo.services;

import com.example.demo.entities.Category;
import com.example.demo.entities.Image;
import com.example.demo.entities.Product;
import com.example.demo.entities.Review;
import com.example.demo.exceptions.AlReadyExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository ;
    @Autowired
    private CategoryRepository categoryRepository ;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll() ;
    }

    @Override
    public Product createProduct(Product product) throws AlReadyExistException {
        for(Product p : productRepository.findAll()){
            if(Objects.equals(product.getName(), p.getName()) && product.getCategory().equals(p.getCategory())){
                throw new AlReadyExistException("Product already exit !!");
            }
        }
        Category category = (Category) Optional.ofNullable(categoryRepository.findByName(product.getCategory().getName()))
                .orElseGet(() -> categoryRepository.save(new Category(product.getCategory().getName())));

        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId) throws NotFoundException {
        return productRepository.findById(productId)
                .orElseThrow(()-> new NotFoundException("Product not found with id : " + productId));
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws NotFoundException {
        Product newProduct = productRepository.findById(productId)
                .orElseThrow(()-> new NotFoundException("Product not found with id : " + productId));
        newProduct.setName(product.getName());
        newProduct.setCategory(product.getCategory());
        newProduct.setPrice(product.getPrice());
        newProduct.setDescription(product.getDescription());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setActive(product.getActive());
        newProduct.setBrand(product.getBrand());
        newProduct.setImages(product.getImages());
        newProduct.setReviews(product.getReviews());
        newProduct.setAverageRating(product.getAverageRating());
        newProduct.setOrderItems(product.getOrderItems());
        newProduct.setReviewsCount(product.getReviewsCount());
        return productRepository.save(newProduct);
    }

    @Override
    public void deleteProduct(Long productId) throws NotFoundException {
        Product newProduct = productRepository.findById(productId)
                .orElseThrow(()-> new NotFoundException("Product not found with id : " + productId));
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> searchProducts(String text) {

        List<Product> resultsProducts = new ArrayList<>();

        for(Product product : productRepository.findAll()){
           if(product.getName().toLowerCase().contains(text.toLowerCase()) ||
                   product.getDescription().toLowerCase().contains(text.toLowerCase()) ||
                   product.getPrice().toString().contains(text.toLowerCase())
           ){
               resultsProducts.add(product);
           }
        }
        return resultsProducts ;
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) throws NotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category Not found with id : " + categoryId));
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> getAvailableProducts() {

        List<Product> resultsProducts = new ArrayList<>();

        for(Product product : productRepository.findAll()){
            if(product.getActive().equals(true)){
                resultsProducts.add(product);
            }
        }

        return resultsProducts ;
    }

    @Override
    public List<Product> getTopRatedProducts() {
        List<Product> products = productRepository.findAll();
        products.sort((product1 , product2 ) -> Double.compare(product2.getAverageRating().toBigInteger().doubleValue() , product1.getAverageRating().doubleValue() ));
        return products;
    }

    @Override
    public void updateQuantity(Long productId, Integer quantity) throws NotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id : " + productId));
        product.setQuantity(quantity);
        productRepository.save(product);
    }

    @Override
    public Product addProductImage(Long productId, Image image) throws NotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id : " + productId));
        product.getImages().add(image);
        productRepository.save(product);
        return product;
    }

    @Override
    public Product removeProductImage(Long productId, Long imageId) throws NotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id : " + productId));
        product.getImages().remove(imageId);
        productRepository.save(product);
        return product ;
    }

    @Override
    public List<Review> getProductReviews(Long productId) throws NotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id : " + productId));
        return product.getReviews();
    }
}
