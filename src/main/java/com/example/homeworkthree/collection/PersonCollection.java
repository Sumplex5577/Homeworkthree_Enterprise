package com.example.homeworkthree.collection;

import com.example.homeworkthree.model.Person;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class PersonCollection {

    public Set<Person> persons = new HashSet<>();
}
