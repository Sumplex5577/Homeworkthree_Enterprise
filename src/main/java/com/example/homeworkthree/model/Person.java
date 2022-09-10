package com.example.homeworkthree.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Person {
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private final Integer id = idCounter.incrementAndGet();

    private String firstName;
    private String lastName;

    private String email;

    @JsonIgnore
    private final List<Cart> carts = new ArrayList<>();

    public Person(@NonNull String firstName, @NonNull String lastName, @NonNull String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(id, person.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Person: " +
                    "(id=" + id +
                    ", first name=" + firstName +
                    ", last name=" + lastName +
                    ", phone number=" + email + ')';
        }
    }