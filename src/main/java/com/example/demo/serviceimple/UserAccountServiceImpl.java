package com.example.demo.service.imple;

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

    // TECHNICAL CONSTRAINT: Must use Constructor Injection in this exact order
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAccount register(UserAccount user) {
        // RULE: Email must be unique
        if (userAccountRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        // RULE: Password stored encoded
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userAccountRepository.save(user);
    }

    @Override
    public UserAccount findByEmailOrThrow(String email) {
        // RULE: Throw ResourceNotFoundException if not found
        return userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public Optional<UserAccount> getByEmail(String email) {
        return userAccountRepository.findByEmail(email);
    }
}