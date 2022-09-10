package com.example.homeworkthree.services;

import com.example.homeworkthree.collection.PersonCollection;
import com.example.homeworkthree.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonCollection personCollection;

    @Override
    public String createPerson(Person person) {
        Person newPerson = new Person();
        newPerson.setFirstName(person.getFirstName());
        newPerson.setLastName(person.getLastName());
        newPerson.setEmail(person.getEmail());

        personCollection.persons.add(person);

        return newPerson.getFirstName();
    }

    @Override
    public Person getPersonById(Integer id) {
       return personCollection.persons.stream().filter(p -> Objects.equals(p.getId(), id)).findAny().get();
    }

}
