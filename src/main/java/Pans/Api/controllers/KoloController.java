package Pans.Api.controllers;

import Pans.Api.models.Kolo;
import Pans.Api.models.Person;
import Pans.Api.repository.KoloRepository;
import Pans.Api.repository.KoloUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Kolo")
@CrossOrigin(origins = "http://localhost:3000")
public class KoloController {

    @Autowired
    private KoloRepository koloRepository;

    @GetMapping("/find")
    public List<Kolo> allKolo(@RequestParam(name = "nazwa", required = true) String nazwa){
        return this.koloRepository.findByNazwaContains(nazwa);
    }

    @PostMapping("/Add")
    public Kolo createPerson(@Valid @RequestBody Kolo kolo) {
        return koloRepository.save(kolo);
    }

    @PutMapping("/Edit")
    public Kolo updatePerson(@RequestBody Kolo kolo){
        Kolo existingKolo=koloRepository.findById(kolo.getId()).orElse(null);
        existingKolo.setNazwa(kolo.getNazwa());
        existingKolo.setOpis(kolo.getOpis());
        existingKolo.setStatus(kolo.getStatus());
        return koloRepository.save(existingKolo);
    }
}
