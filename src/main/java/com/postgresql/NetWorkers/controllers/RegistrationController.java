package com.postgresql.NetWorkers.controllers;

import com.postgresql.NetWorkers.entities.LoginRequest;
import com.postgresql.NetWorkers.entities.Registration;
import com.postgresql.NetWorkers.enums.UserType;
import com.postgresql.NetWorkers.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200, http://localhost:3333, http://localhost:5433, http://localhost:80")
public class RegistrationController {
    @GetMapping("/registrations")
    public ResponseEntity<List<Registration>> getAllRegistrations() {
        List<Registration> registrations = registrationService.getAll();
        return new ResponseEntity<>(registrations, HttpStatus.OK);
    }

    private final com.postgresql.NetWorkers.services.RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registrations")
    public ResponseEntity<Registration> registerUser(@RequestBody Registration registration) {
        Registration savedRegistration = registrationService.register(registration);
        return new ResponseEntity<>(savedRegistration, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<UserType> loginUser(@RequestBody LoginRequest loginRequest) {
        UserType userType = registrationService.checkUserType(loginRequest.getEmail(), loginRequest.getPassword());

        if (userType != null) {
            return new ResponseEntity<>(userType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
