package Pans.Api.controllers;


import Pans.Api.models.Kolo;
import Pans.Api.models.KoloUser;
import Pans.Api.models.Person;
import Pans.Api.repository.KoloUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/KoloUser")
@CrossOrigin(origins = "http://localhost:3000")
public class KoloUserController {

    @Autowired
    private KoloUserRepository koloUserRepository;

    @GetMapping("/FindByUser")
    public List<KoloUser> AllGroup(@RequestParam(name = "Person", required = true) String email){
        return this.koloUserRepository.findByUserIdUser_Email(email);
    }

    @GetMapping("/FindByKolo")
    public List<KoloUser> AllKolo(@RequestParam(name = "Kolo", required = true) Kolo kolo){
        return this.koloUserRepository.findByKoloIdKola(kolo);
    }

    @PostMapping("/Add")
    public KoloUser createKoloUser(@Valid @RequestBody KoloUser kolo) {
        return koloUserRepository.save(kolo);
    }

    @PutMapping("/Edit")
    public KoloUser updatePerson(@RequestBody KoloUser kolo){
        KoloUser existingKolo=koloUserRepository.findById(kolo.getId()).orElse(null);
        existingKolo.setPermissionAdmin(kolo.getPermissionAdmin());
        existingKolo.setEventsAdder(kolo.getEventsAdder());
        existingKolo.setManageUser(kolo.getManageUser());
        existingKolo.setChatWriter(kolo.getChatWriter());
        return koloUserRepository.save(existingKolo);
    }
}
