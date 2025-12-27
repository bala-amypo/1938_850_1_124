package com.example.demo.service;

import com.example.demo.entity.UserAccount;

public interface UserAccountService {
    UserAccount register(UserAccount user);
    String login(String email, String password);
    UserAccount findByEmailOrThrow(String email);
}