package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUserById(Integer userId);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUserById(Integer userId);
}

