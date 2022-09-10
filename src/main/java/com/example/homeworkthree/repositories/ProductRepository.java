package com.example.homeworkthree.repositories;

import com.example.homeworkthree.model.Product;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class ProductRepository {
    private final Map<Integer, Product> products = new HashMap<>();
}
