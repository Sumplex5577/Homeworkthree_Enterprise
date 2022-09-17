package com.example.homeworkthree.services;


import com.example.homeworkthree.exceptions.NotFoundException;
import com.example.homeworkthree.models.Product;

import java.util.Collection;
public interface ProductService {

        Product createProduct(Product product);

        Product getProductById(Integer productId) throws NotFoundException;

        Product updateProduct(Integer productId, Product product) throws NotFoundException;

        void deleteProduct(Integer productId) throws NotFoundException;

        Collection<Product> getAllProducts();

}


