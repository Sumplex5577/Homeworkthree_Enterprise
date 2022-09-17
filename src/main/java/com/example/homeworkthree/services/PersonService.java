package com.example.homeworkthree.services;
import com.example.homeworkthree.exceptions.NotFoundException;
import com.example.homeworkthree.models.Person;
import java.util.Collection;


public interface PersonService {

    Person createPerson(Person person);

    Person getPersonById(Integer id) throws NotFoundException;

    Person updatePerson(Integer Id, Person person) throws NotFoundException;

    void deletePerson(Integer id) throws NotFoundException;

    Collection<Person> getAllPersons();
}