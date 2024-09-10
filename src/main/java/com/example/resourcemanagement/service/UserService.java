package com.example.resourcemanagement.service;

import com.example.resourcemanagement.model.User;
import com.example.resourcemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user){
        User existingUser = getUserById(id);
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id){
        User user = getUserById(id);
        userRepository.delete(user);
    }
}
