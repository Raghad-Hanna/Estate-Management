package com.raghad.estate.controllers;

//import com.raghad.estate.forms.RegistrationForm;
import com.raghad.estate.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.raghad.estate.repositories.UserRepository;

@RestController
@RequestMapping("api/register")
public class SignupController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SignupController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return "Created The User Successfully";
    }
}
