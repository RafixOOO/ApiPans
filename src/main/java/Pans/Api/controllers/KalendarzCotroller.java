package Pans.Api.controllers;

import Pans.Api.exceptions.ResourceNotFoundException;
import Pans.Api.models.Event;
import Pans.Api.models.Kalendarz;
import Pans.Api.models.Person;
import Pans.Api.repository.KalendarzRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Kalendarz")
@CrossOrigin(origins = "http://localhost:3000")
public class KalendarzCotroller {

    @Autowired
    private KalendarzRepository kalendarzRepository;

    @GetMapping("/{person}")
    public List<Kalendarz> allKalendarz(@PathVariable(value = "person") Person person){

            return this.kalendarzRepository.findByIdPerson(person);

    }

    @PostMapping("/Add")
    public Kalendarz createKalendarz(@Valid @RequestBody Kalendarz kalendarz) {
        return kalendarzRepository.save(kalendarz);
    }

    @DeleteMapping("/Delete/{id}")
    public Map<String, Boolean> deleteKalendarz(@PathVariable(value = "id") Integer kalendarztId)
            throws ResourceNotFoundException {
        Kalendarz kalendarz = kalendarzRepository.findById(kalendarztId)
                .orElseThrow(() -> new ResourceNotFoundException("Kalendarz not found for this id :: " + kalendarztId));

        kalendarzRepository.delete(kalendarz);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
