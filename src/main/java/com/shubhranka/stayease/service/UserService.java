package com.shubhranka.stayease.service;

import com.shubhranka.stayease.entities.User;
import com.shubhranka.stayease.exceptions.ValidationException;
import com.shubhranka.stayease.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {
        String password = user.getPassword();
        if (password == null || password.length() < 8) {
            throw new ValidationException("Password must be at least 8 characters long");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "User registered successfully";
    }

}
