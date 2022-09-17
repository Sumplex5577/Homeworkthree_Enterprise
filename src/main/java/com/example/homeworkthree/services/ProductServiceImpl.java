package com.example.homeworkthree.services;


import com.example.homeworkthree.exceptions.NotFoundException;
import com.example.homeworkthree.models.Product;
import com.example.homeworkthree.repositories.ProductRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private static Integer productCount = 0;

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        product.setProductId(++productCount);
        productRepository.getProducts().put(product.getProductId(), product);
        return product;
    }

    @Override
    public Product getProductById(Integer productId) throws NotFoundException {
        if (productRepository.getProducts().containsKey(productId)) {
            return productRepository.getProducts().get(productId);
        } else {
            throw new NotFoundException("Product with ID #" + productId + " is not found");
        }
    }

    @Override
    public Product updateProduct(Integer productId, Product product) throws NotFoundException {
        if (productRepository.getProducts().containsKey(productId)) {
            Product tmp = productRepository.getProducts().get(productId);
            tmp.setName(product.getName());
            tmp.setPrice(product.getPrice());
            return tmp;
        } else {
            throw new NotFoundException("Person with ID #" + productId + " is not found");
        }
    }

    @Override
    public void deleteProduct(Integer productId) throws NotFoundException {
        if (productRepository.getProducts().containsKey(productId)) {
            productRepository.getProducts().remove(productId);
        } else {
            throw new NotFoundException("Product with ID #" + productId + " is not found");
        }
    }

    @Override
    public Collection<Product> getAllProducts() {
        return productRepository.getProducts().values();
    }
}