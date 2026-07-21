package com.shopsphere.backend.service;

import com.shopsphere.backend.dto.ProductRequest;
import com.shopsphere.backend.entity.Product;
import com.shopsphere.backend.exception.ProductNotFoundException;
import com.shopsphere.backend.repository.ProductRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Get product by ID
    public Product getProductById(@NonNull Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product with id " + id + " not found"));
    }

    // Create product
    public Product createProduct(ProductRequest request) {

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        return repository.save(product);
    }

    // Update product
    public Product updateProduct(@NonNull Long id, ProductRequest request) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product with id " + id + " not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        return repository.save(product);
    }

    // Delete product
    public void deleteProduct(@NonNull Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product with id " + id + " not found"));

        repository.delete(product);
    }
}