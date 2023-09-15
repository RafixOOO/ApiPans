package Pans.Api.controllers;

import Pans.Api.exceptions.ResourceNotFoundException;
import Pans.Api.models.Komentarz;
import Pans.Api.repository.KomentarzRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/Komentarz")
public class KomentarzController {

    @Autowired
    private KomentarzRepository komentarzRepository;

    @PostMapping("/Add")
    public Komentarz create(@Valid @RequestBody Komentarz komentarz) {
        return komentarzRepository.save(komentarz);
    }

    @DeleteMapping("/Delete/{id}")
    public Map<String, Boolean> deleteEvent(@PathVariable(value = "id") Integer komentarzId)
            throws ResourceNotFoundException {
        Komentarz komentarz = komentarzRepository.findById(komentarzId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + komentarzId));

        komentarzRepository.delete(komentarz);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
