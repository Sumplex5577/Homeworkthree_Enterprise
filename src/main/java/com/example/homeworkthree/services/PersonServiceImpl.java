package com.example.homeworkthree.services;



import com.example.homeworkthree.exceptions.NotFoundException;
import com.example.homeworkthree.models.Person;
import com.example.homeworkthree.repositories.PersonRepository;
import lombok.NonNull;

import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class PersonServiceImpl implements PersonService {

    private static Integer personCount = 0;

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person createPerson(Person person) {
        person.setId(++personCount);
        personRepository.getPersons().put(person.getId(), person);
        return person;
    }

    @Override
    public Person getPersonById(Integer id) throws NotFoundException {
        if (personRepository.getPersons().containsKey(id)) {
            return personRepository.getPersons().get(id);
        } else {
            throw new NotFoundException("Person with ID #" + id + " is not found");
        }
    }

    @Override
    public void deletePerson(Integer id) throws NotFoundException {
        if (personRepository.getPersons().containsKey(id)) {
            personRepository.getPersons().remove(id);
        } else {
            throw new NotFoundException("Person with ID #" + id + " is not found");
        }
    }

    @Override
    public Person updatePerson(Integer id, Person person) throws NotFoundException {
        if (personRepository.getPersons().containsKey(id)) {
            Person tmp = personRepository.getPersons().get(id);
            tmp.setFirstName(person.getFirstName());
            tmp.setLastName(person.getLastName());
            tmp.setEmail(person.getEmail());
            tmp.setPhone(person.getPhone());
            return tmp;
        } else {
            throw new NotFoundException("Person with ID #" + id + " is not found");
        }
    }

    @Override
    public Collection<Person> getAllPersons() {
        return personRepository.getPersons().values();
    }
}