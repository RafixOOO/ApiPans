package Pans.Api.controllers;

import Pans.Api.models.Chat;
import Pans.Api.models.Kolo;
import Pans.Api.models.Person;
import Pans.Api.repository.ChatRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Chat")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    @Autowired
    private ChatRepository chatrepository;

    @PostMapping("/Add")
    public Chat createPerson(@Valid @RequestBody Chat chat) {
        return chatrepository.save(chat);
    }

    @GetMapping("/find")
    public List<Chat> allPersons(@RequestParam(name = "id", required = true) Kolo kolo){
        return this.chatrepository.findByIdKola(kolo);
    }
}
