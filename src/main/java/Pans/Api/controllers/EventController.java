package Pans.Api.controllers;

import Pans.Api.exceptions.ResourceNotFoundException;
import Pans.Api.models.Event;
import Pans.Api.models.Kolo;
import Pans.Api.models.Person;
import Pans.Api.repository.EventRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Event")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {
    @Autowired
    private EventRepository eventRepository;


    @GetMapping("/idKolo")
    public List<Event> allKolo(@Valid @RequestBody Kolo kolo){
        return this.eventRepository.findByIdKolo(kolo);
    }

    @GetMapping("/public/All")
    public List<Event> allEvent(){
        return this.eventRepository.findByStatus(true);
    }

    @PostMapping("/Add")
    public Event createEvent(@Valid @RequestBody Event event) {
        return eventRepository.save(event);
    }

    @DeleteMapping("/Delete/{id}")
    public Map<String, Boolean> deleteEvent(@PathVariable(value = "id") Integer eventId)
            throws ResourceNotFoundException {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + eventId));

        eventRepository.delete(event);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/Edit")
    public Event updateEvenet(@RequestBody Event event){
        Event existingEvent=eventRepository.findById(event.getId()).orElse(null);
        existingEvent.setData(event.getData());
        existingEvent.setOpis(event.getOpis());
        existingEvent.setMiejsce(event.getMiejsce());
        existingEvent.setNazwa(event.getNazwa());
        existingEvent.setStatus(event.getStatus());
        existingEvent.setIdKolo(event.getIdKolo());
        return eventRepository.save(existingEvent);
    }
}
