package com.example.homeworkthree.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Product {

    private Integer productId;
    private String name;
    private Double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getProductId().equals(product.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId());
    }
}


