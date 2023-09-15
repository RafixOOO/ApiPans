package Pans.Api.controllers;

import Pans.Api.models.Person;
import Pans.Api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Persons")
public class PersonsController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/findOne")
    public Optional<Person> OnePerson(@RequestParam(name = "id") Integer id){
        return this.personRepository.findById(id);
    }

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

