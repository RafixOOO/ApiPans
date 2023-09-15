package Pans.Api.controllers;

import Pans.Api.models.Kolo;
import Pans.Api.repository.KoloRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Kolo")
public class KoloController {

    @Autowired
    private KoloRepository koloRepository;

    @GetMapping("/find")
    public Optional<Kolo> allKolo(@RequestParam(name = "id", required = true) Integer id){
        return this.koloRepository.findById(id);
    }

    @GetMapping("/findPiece")
    public List<Kolo> allKolo1(@RequestParam(name = "nazwa", required = true) String nazwa){
        return this.koloRepository.findByNazwaContainsAndStatus(nazwa,true);
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
