package com.example.homeworkthree.services;


import com.example.homeworkthree.dtos.AddPersonDto;

import com.example.homeworkthree.dtos.PersonCsvDto;
import com.example.homeworkthree.dtos.UpdatePersonDto;
import com.example.homeworkthree.exceptions.NotFoundException;
import com.example.homeworkthree.model.Person;
import com.example.homeworkthree.repositories.PersonRepository;
import lombok.NonNull;

import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class PersonServiceImpl implements PersonService {
    private static final String FILE_PATH = "src/main/resources/persons.csv";
    private final PersonRepository personRepository;
    private final CsvWriter<PersonCsvDto> csvWriter;

    public PersonServiceImpl(PersonRepository personRepository, CsvWriter<PersonCsvDto> csvWriter) {
        this.personRepository = personRepository;
        this.csvWriter = csvWriter;
    }

    @Override
    public String addPerson(@NonNull AddPersonDto personDTO) {
        Person person = new Person(personDTO.getFirstName(), personDTO.getLastName(), personDTO.getEmail());
        personRepository.getPersons().put(person.getId(), person);
        csvWriter.write(FILE_PATH, buildPersonCvsDto(person, "added"));
        return person.toString();
    }

    private PersonCsvDto buildPersonCvsDto(Person person, String status) {
        return PersonCsvDto.builder().id(person.getId()).firstName(person.getFirstName())
                .lastName(person.getLastName()).email(person.getEmail())
                .status(status).build();
    }

    @Override
    public String removePersonById(Integer id) throws NotFoundException {
        if (personRepository.getPersons().containsKey(id)) {
            Person person = personRepository.getPersons().remove(id);
            csvWriter.write(FILE_PATH, buildPersonCvsDto(person, "deleted"));
            return person.toString();
        } else {
            throw new NotFoundException("Person with ID #" + id + " is not found");
        }
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
    public Collection<Person> getAllPersons() {
        return personRepository.getPersons().values();
    }

    @Override
    public String updatePersonFirstNameById(Integer id, @NonNull UpdatePersonDto dto) throws NotFoundException {
        if (personRepository.getPersons().containsKey(id)) {
            Person person = personRepository.getPersons().get(id);
            person.setFirstName(dto.getFirstName());
            csvWriter.write(FILE_PATH, buildPersonCvsDto(person, "updated"));
            return person.toString();
        } else {
            throw new NotFoundException("Person with ID #" + id + " is not found");
        }
    }

    @Override
    public String updatePersonLastNameById(Integer id, @NonNull UpdatePersonDto dto) throws NotFoundException {
        if (personRepository.getPersons().containsKey(id)) {
            Person person = personRepository.getPersons().get(id);
            person.setLastName(dto.getLastName());
            csvWriter.write(FILE_PATH, buildPersonCvsDto(person, "updated"));
            return person.toString();
        } else {
            throw new NotFoundException("Person with ID #" + id + " is not found");
        }
    }

    @Override
    public String updatePersonPhoneNumberById(Integer id, @NonNull UpdatePersonDto dto) throws NotFoundException {
        if (personRepository.getPersons().containsKey(id)) {
            Person person = personRepository.getPersons().get(id);
            person.setEmail(dto.getEmail());
            csvWriter.write(FILE_PATH, buildPersonCvsDto(person, "updated"));
            return person.toString();
        } else {
            throw new NotFoundException("Person with ID #" + id + " is not found");
        }
    }
}
