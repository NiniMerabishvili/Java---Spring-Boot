package com.postgresql.NetWorkers.services;

import com.postgresql.NetWorkers.entities.LoginRequest;
import com.postgresql.NetWorkers.entities.Order;
import com.postgresql.NetWorkers.entities.Registration;
import com.postgresql.NetWorkers.enums.UserType;
import com.postgresql.NetWorkers.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;


    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public List<Registration> getAll() {
        return registrationRepository.findAll();
    }

    public Registration getByName(String name) {
        return registrationRepository.findByName(name).orElse(null);
    }

    public Registration register(Registration registration) {
        // Implement logic to save the registration in the repository
        // For example, you can use registrationRepository.save(registration);
        UserType userType = determineUserType(registration.getEmail(), registration.getPassword());

        registration.setUserType(userType);

        return registrationRepository.save(registration);
    }


    //login
    private UserType determineUserType(String email, String password) {
        // Your logic to determine user type based on email and password
        // For example, check if email and password match admin credentials
        if ("admin@gmail.com".equals(email) && "admin1".equals(password)) {
            return UserType.ADMIN;
        } else {
            return UserType.CUSTOMER;
        }
    }

    public UserType checkUserType(String email, String password) {
        Registration user = registrationRepository.findByEmailAndPassword(email, password);

        if (user != null) {
            return user.getUserType();
        }

        return null;
    }
//    public Registration login(LoginRequest loginRequest) {
//        String email = loginRequest.getEmail();
//        String password = loginRequest.getPassword();
//
//        // Fetch user by email and password from the repository
//        Registration user = registrationRepository.findByEmailAndPassword(email, password);
//
//        // Check if the user exists and handle admin login based on predefined credentials
//        if (user != null) {
//            user.setUserType(determineUserType(email, password));
//            return user;
//        } else {
//            return null; // Invalid login credentials
//        }
//    }
}
