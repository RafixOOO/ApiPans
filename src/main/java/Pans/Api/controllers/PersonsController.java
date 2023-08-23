package Pans.Api.controllers;

import Pans.Api.exceptions.ResourceNotFoundException;
import Pans.Api.models.Person;
import Pans.Api.repository.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PersonsController {

    @Autowired
    private PersonRepository personRepository;
    @GetMapping("/Persons/ByNameSurname/{imie}/{nazwisko}")
    public List<Person> allPersons(@PathVariable(value = "imie") String imie, @PathVariable(value = "nazwisko") String nazwisko){
        return this.personRepository.findByImieAndNazwisko(imie,nazwisko);
    }

    @PostMapping("/Persons/Add")
    public Person createPerson(@Valid @RequestBody Person person) {
        return personRepository.save(person);
    }

    @PutMapping("/Persons/Edit")
    public Person updatePerson(@RequestBody Person person){
        Person existingPerson=personRepository.findById(person.getId()).orElse(null);
        existingPerson.setImie(person.getImie());
        existingPerson.setNazwisko(person.getNazwisko());
        return personRepository.save(existingPerson);
    }
}

