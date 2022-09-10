package com.example.homeworkthree.services;


import com.example.homeworkthree.dtos.AddProductDto;
import com.example.homeworkthree.dtos.GetProductDto;
import com.example.homeworkthree.dtos.UpdateProductDto;
import com.example.homeworkthree.exceptions.NotFoundException;
import com.example.homeworkthree.model.Product;

import java.util.Collection;

public interface ProductService {
        String createProduct(AddProductDto dto);

        String removeProductById(Integer id) throws NotFoundException;

        Collection<Product> getAllProducts();

        Product getProductById(Integer id) throws NotFoundException;

        Product getProductByName(GetProductDto dto) throws NotFoundException;

        String updateProductNameById(Integer id, UpdateProductDto dto) throws NotFoundException;

        String updateProductPriceById(Integer id, UpdateProductDto dto) throws NotFoundException;

    }

