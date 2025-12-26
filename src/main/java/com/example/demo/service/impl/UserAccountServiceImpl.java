package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    // Technical Constraint: Use Constructor Injection in this exact order
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAccount register(UserAccount user) {
        // Rule: Email must be unique
        if (userAccountRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        // Rule: Password stored encoded
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userAccountRepository.save(user);
    }

    @Override
    public UserAccount findByEmailOrThrow(String email) {
        // Rule: Throw ResourceNotFoundException if not found
        return userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    // FIX: Implementing the missing login method required by your interface
    @Override
    public UserAccount login(String email, String password) {
        UserAccount user = findByEmailOrThrow(email);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }
        return user;
    }
}