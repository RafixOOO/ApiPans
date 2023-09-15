package Pans.Api.controllers;

import Pans.Api.models.Event;
import Pans.Api.models.Person;
import Pans.Api.models.User;
import Pans.Api.repository.EventRepository;
import Pans.Api.repository.PersonRepository;
import Pans.Api.repository.UserRepository;
import Pans.Api.service.JwtRequest;
import Pans.Api.service.JwtResponse;
import Pans.Api.security.JwtHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/auth/")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private JwtHelper helper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EventRepository eventRepository;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/Check")
    public User allPersons(@RequestParam(name = "email", required = true) String email){
        return this.userRepository.findByEmail(email);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @PostMapping("/AddUser")
    public User createKoloUser(@Valid @RequestBody User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setEmail(user.getEmail());
        return userRepository.save(user);
    }

    @PostMapping("/AddPerson")
    public Person createPerson(@Valid @RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping("/public/All")
    public List<Event> allEvent(){
        return this.eventRepository.findByStatus(true);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
