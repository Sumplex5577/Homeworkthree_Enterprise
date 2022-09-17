package com.example.homeworkthree.repositories;

import com.example.homeworkthree.models.Person;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class PersonRepository {
    private final Map<Integer, Person> persons = new HashMap<>();
}
