package com.example.demo.service; // or your preferred package

import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
import org.springframework.stereotype.Service; // Mandatory import
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

@Service // <--- THIS IS WHAT SPRING LOOKS FOR
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    // If you are using Autowired in your controllers, 
    // you can use @Autowired or @RequiredArgsConstructor here too
    private final PasswordEncoder passwordEncoder; 

    @Override
    public UserAccount register(UserAccount user) {
        // Your logic to save user
        return null; 
    }

    @Override
    public String login(String email, String password) {
        // Your logic
        return null;
    }

    @Override
    public UserAccount getByEmail(String email) {
        return null;
    }

    @Override
    public UserAccount findByEmailOrThrow(String email) {
        // Your logic
        return null;
    }
}