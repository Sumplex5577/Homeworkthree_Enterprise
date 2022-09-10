package com.example.homeworkthree.services;

import com.example.homeworkthree.model.Person;

import java.util.Collection;


public interface PersonService {
    String createPerson(Person person);

    Person getPersonById(Integer id);

    String removePersonById(Integer id) throws NotFoundException;

    Collection<Person> getAllPersons();

    String updatePersonFirstNameById(Integer id, UpdatePersonDto dto) throws NotFoundException;

    String updatePersonLastNameById(Integer id, UpdatePersonDto dto) throws NotFoundException;

    String updatePersonPhoneNumberById(Integer id, UpdatePersonDto dto) throws NotFoundException;
}

