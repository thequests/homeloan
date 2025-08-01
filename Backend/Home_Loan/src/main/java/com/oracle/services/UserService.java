package com.oracle.services;

import com.oracle.entities.User;
import com.oracle.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User registerUser(User user) {
        // In production, use BCrypt or a strong hash. Here, just for demo:
        user.setPassword(Integer.toHexString(user.getPassword().hashCode()));
        return userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        String hashed = Integer.toHexString(password.hashCode());
        return userRepository.findByEmailAndPassword(email, hashed);
    }
} 