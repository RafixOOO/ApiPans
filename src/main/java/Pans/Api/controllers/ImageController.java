package Pans.Api.controllers;

import Pans.Api.models.Event;
import Pans.Api.models.Kolo;
import Pans.Api.models.Person;
import Pans.Api.repository.EventRepository;
import Pans.Api.repository.KoloRepository;
import Pans.Api.repository.PersonRepository;
import Pans.Api.service.ImageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    @PostMapping(value = "/PersonImage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Person updatePerson(@RequestParam Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        Person existingPerson=personRepository.findById(id).orElse(null);
        String filename = imageService.uploadImage(file);
        existingPerson.setImage(filename);
        return personRepository.save(existingPerson);
    }

    @PostMapping(value = "/KoloImage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Kolo updateUser(@RequestParam Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        Kolo existingPerson=koloRepository.findById(id).orElse(null);
        String filename = imageService.uploadImage(file);
        existingPerson.setImage(filename);
        return koloRepository.save(existingPerson);
    }

    @PostMapping(value = "/EventImage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Event updateEvent(@RequestParam Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        Event existingPerson=eventRepository.findById(id).orElse(null);
        String filename = imageService.uploadImage(file);
        existingPerson.setImage(filename);
        return eventRepository.save(existingPerson);
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadImages(@RequestParam List<String> fileNames) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(baos);

        for (String fileName : fileNames) {
            Resource resource = imageService.downloadImage(fileName);
            byte[] fileBytes = IOUtils.toByteArray(resource.getInputStream());

            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            zipOut.write(fileBytes);
            zipOut.closeEntry();
        }

        zipOut.finish();
        zipOut.close();

        byte[] zipBytes = baos.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(zipBytes);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"images.zip\"")
                .body(resource);
    }

}
