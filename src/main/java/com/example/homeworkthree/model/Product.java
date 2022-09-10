package com.example.homeworkthree.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Product {

    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private final Integer productId;
    @Setter
    private String productName;
    @Setter
    private Double price;

    public Product(@NonNull String productName, @NonNull Double price) {
        this.productId = idCounter.incrementAndGet();
        this.productName = productName;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId.equals(product.productId)
                && Objects.equals(productName, product.productName)
                && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);

    }

    @Override
    public String toString() {
        return "Product: " + "(id=" + productId + ", "
                + "name=" + productName + ", " + "price=" + price + ')';
    }

}



