package Pans.Api.controllers;

import Pans.Api.models.Event;
import Pans.Api.models.Kolo;
import Pans.Api.models.Person;
import Pans.Api.models.User;
import Pans.Api.repository.EventRepository;
import Pans.Api.repository.KoloRepository;
import Pans.Api.repository.PersonRepository;
import Pans.Api.repository.UserRepository;
import Pans.Api.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/Image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private KoloRepository koloRepository;

    @PostMapping("/PersonImage")
    public Person updatePerson(@RequestParam Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        Person existingPerson=personRepository.findById(id).orElse(null);
        String filename = imageService.uploadImage(file);
        existingPerson.setImage(filename);
        return personRepository.save(existingPerson);
    }

    @PostMapping("/KoloImage")
    public Kolo updateUser(@RequestParam Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        Kolo existingPerson=koloRepository.findById(id).orElse(null);
        String filename = imageService.uploadImage(file);
        existingPerson.setImage(filename);
        return koloRepository.save(existingPerson);
    }

    @PostMapping("/EventImage")
    public Event updateEvent(@RequestParam Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        Event existingPerson=eventRepository.findById(id).orElse(null);
        String filename = imageService.uploadImage(file);
        existingPerson.setImage(filename);
        return eventRepository.save(existingPerson);
    }

    @GetMapping(value = "/download/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> download(@PathVariable String fileName) throws IOException {
        Resource resource = imageService.downloadImage(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
