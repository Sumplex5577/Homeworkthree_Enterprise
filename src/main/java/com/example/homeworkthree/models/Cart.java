package com.example.homeworkthree.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Cart {

    private final List<Product> products = new ArrayList<>();

    private Integer cartId;

    private Person person;

    private BigDecimal sum = new BigDecimal("0.0");

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return getCartId().equals(cart.getCartId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCartId());
    }
}
