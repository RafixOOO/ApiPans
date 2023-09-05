package Pans.Api.controllers;

import Pans.Api.models.KoloUser;
import Pans.Api.models.User;
import Pans.Api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/User")
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
}
