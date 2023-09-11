package Pans.Api.controllers;

import Pans.Api.exceptions.ResourceNotFoundException;
import Pans.Api.models.Event;
import Pans.Api.models.KoloUser;
import Pans.Api.models.Person;
import Pans.Api.models.User;
import Pans.Api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/User")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/Current")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }

    @PostMapping("/Add")
    public User createKoloUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/Edit")
    public User updatePerson(@RequestBody User user){
        User existingUser=userRepository.findById(user.getId()).orElse(null);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(user.getPassword());
        existingUser.setPassword(hashedPassword);
        return userRepository.save(existingUser);
    }

    @DeleteMapping("/Delete/{id}")
    public Map<String, Boolean> deleteEvent(@PathVariable(value = "id") Integer userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
