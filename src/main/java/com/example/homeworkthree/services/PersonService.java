package com.example.homeworkthree.services;

import com.example.homeworkthree.dtos.AddPersonDto;
import com.example.homeworkthree.dtos.CreatePersonDto;
import com.example.homeworkthree.dtos.UpdatePersonDto;
import com.example.homeworkthree.exceptions.NotFoundException;
import com.example.homeworkthree.model.Person;

import java.util.Collection;


public interface PersonService {
    String addPerson(AddPersonDto personDTO);

    Person getPersonById(Integer id) throws NotFoundException;

    String removePersonById(Integer id) throws NotFoundException;

    Collection<Person> getAllPersons();

    String updatePersonFirstNameById(Integer id, UpdatePersonDto dto) throws NotFoundException;

    String updatePersonLastNameById(Integer id, UpdatePersonDto dto) throws NotFoundException;

    String updatePersonPhoneNumberById(Integer id, UpdatePersonDto dto) throws NotFoundException;
}

