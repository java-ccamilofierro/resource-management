package com.example.resourcemanagement.service;

import com.example.resourcemanagement.model.User;
import com.example.resourcemanagement.repository.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User createUser(User user){
        if(userRepository.findByUserName(user.getUsername()).isPresent()){
            throw new ValidationException("Username alredy exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user){
        User existingUser = getUserById(id);
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
