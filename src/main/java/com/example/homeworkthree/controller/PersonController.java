package com.example.homeworkthree.controller;
import com.example.homeworkthree.dtos.AddPersonDto;
import com.example.homeworkthree.dtos.CreatePersonDto;
import com.example.homeworkthree.model.Person;
import com.example.homeworkthree.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class PersonController {
    @Autowired
    private PersonService personService;
    @PostMapping("/create")
    public ResponseEntity<String> createPerson(@RequestBody AddPersonDto personDTO) {
        return new ResponseEntity<>(personService.addPerson(personDTO) + " was added", HttpStatus.OK);
    }
    @GetMapping("/get_person_by/{id}")
    public Person getPersonById(@PathVariable String id) {
        return personService.getPersonById(Integer.valueOf(id));
    }
}
