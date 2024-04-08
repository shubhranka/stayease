package com.shubhranka.stayease.service;

import com.shubhranka.stayease.dto.LoginRequest;
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

    @Autowired
    private JwtService jwtService;

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

    public String loginUser(LoginRequest user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            throw new ValidationException("User not found");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new ValidationException("Invalid password");
        }
        return jwtService.generateToken(existingUser.getUsername());
    }
}
