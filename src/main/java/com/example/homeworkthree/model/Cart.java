package com.example.homeworkthree.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Cart {
    private Person person;
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    private final Integer id = idCounter.incrementAndGet();
    private BigDecimal sum = new BigDecimal("0.0");

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cart: " +
                "(id=" + id +
                ", owner: " + person.getId() +
                ", sum: " + sum + ')';
    }
}
