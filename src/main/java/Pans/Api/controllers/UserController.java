package Pans.Api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/User")
public class UserController {

    @GetMapping("/Current")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }
}
