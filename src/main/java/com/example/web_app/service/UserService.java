package com.example.web_app.service;

import com.example.web_app.model.User;
import com.example.web_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<Map<String, String>> getAllUserSummaries() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> Map.of(
                        "name", user.getName(),
                        "surname", user.getSurname(),
                        "userID", user.getUserID().toString() 
                ))
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
