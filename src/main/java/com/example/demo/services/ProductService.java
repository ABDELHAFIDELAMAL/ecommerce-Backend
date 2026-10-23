package com.example.demo.services;


import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.entities.Review;
import com.example.demo.exceptions.AlReadyExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ImageRepository;
import com.example.demo.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository ;
    @Autowired
    private CategoryRepository categoryRepository ;
    @Autowired
    private ImageRepository imageRepository ;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll() ;
    }

    @Override
    public Product createProduct(Product product) throws AlReadyExistException {

        if (product.getCategory() == null || product.getCategory().getName() == null) {
            throw new IllegalArgumentException("Category name is required");
        }

        String categoryName = product.getCategory().getName();

        Category category = categoryRepository.findByName(categoryName)
                .orElseGet(() -> {
                    Category c = new Category();
                    c.setName(categoryName);
                    return categoryRepository.save(c);
                });

        product.setCategory(category);
        product.setReviewsCount(0);
        if (productRepository.existsByNameAndBrandAndCategoryName(product.getName(), product.getBrand(), product.getCategory().getName())) {
            throw new AlReadyExistException("Product already exists !!");
        }

        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId) throws NotFoundException {
        return productRepository.findById(productId)
                .orElseThrow(()-> new NotFoundException("Product not found with id : " + productId));
    }

    @Override
    @Transactional
    public Product updateProduct(Long productId, Product product) throws NotFoundException, AlReadyExistException {

        Product existing = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id : " + productId));

        String newName  = product.getName()  != null ? product.getName()  : existing.getName();
        String newBrand = product.getBrand() != null ? product.getBrand() : existing.getBrand();
        String categoryName = (product.getCategory() != null && product.getCategory().getName() != null)
                ? product.getCategory().getName()
                : existing.getCategory().getName();

        if (productRepository.existsByNameAndBrandAndCategoryName(
                newName, newBrand, categoryName)) {
            throw new AlReadyExistException("Product already exists !!");
        }

        Category category = categoryRepository.findByName(categoryName)
                .orElseGet(() -> {
                    Category c = new Category();
                    c.setName(categoryName);
                    return categoryRepository.save(c);
                });

        existing.setName(newName);
        existing.setBrand(newBrand);
        existing.setCategory(category);

        if (product.getPrice() != null)       existing.setPrice(product.getPrice());
        if (product.getQuantity() != null)    existing.setQuantity(product.getQuantity());
        if (product.getDescription() != null) existing.setDescription(product.getDescription());
        if (product.getActive() != null)      existing.setActive(product.getActive());

        return productRepository.save(existing);
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
                   product.getPrice().toString().contains(text.toLowerCase()) ||
                   product.getBrand().toLowerCase().contains(text.toLowerCase())
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
    public List<Review> getProductReviews(Long productId) throws NotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id : " + productId));
        return product.getReviews();
    }

}
