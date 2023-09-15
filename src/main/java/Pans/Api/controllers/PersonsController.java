package Pans.Api.controllers;

import Pans.Api.exceptions.ResourceNotFoundException;
import Pans.Api.models.Event;
import Pans.Api.models.Person;
import Pans.Api.repository.PersonRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Persons")
public class PersonsController {
    @Autowired
    private PersonRepository personRepository;
    @GetMapping("/find")
    public List<Person> allPersons(@RequestParam(name = "imie", required = false) String imie,
                                   @RequestParam(name = "nazwisko", required = false) String nazwisko){
        return this.personRepository.findByImieContainsOrNazwiskoContainsOrImieContainsAndNazwiskoContains(imie, nazwisko,imie,nazwisko);
    }

    @PutMapping("/Edit")
    public Person updatePerson(@RequestBody Person person){
        Person existingPerson=personRepository.findById(person.getId()).orElse(null);
        existingPerson.setImie(person.getImie());
        existingPerson.setNazwisko(person.getNazwisko());
        return personRepository.save(existingPerson);
    }
}

