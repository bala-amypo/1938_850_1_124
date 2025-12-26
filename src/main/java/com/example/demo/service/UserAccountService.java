package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;

public interface UserAccountService {
    UserAccount register(UserAccount user);
    String login(String email, String password);
    UserAccount getByEmail(String email);
    UserAccount findByEmailOrThrow(String email);
}