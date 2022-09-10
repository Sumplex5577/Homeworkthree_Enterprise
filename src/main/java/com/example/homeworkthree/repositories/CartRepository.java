package com.example.homeworkthree.repositories;


import com.example.homeworkthree.model.Cart;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class CartRepository {
    private final Map<Integer, Cart> carts = new HashMap<>();
}
