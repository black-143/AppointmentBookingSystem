package com.pravallika.assessment.service;

import com.pravallika.assessment.model.User;
import com.pravallika.assessment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User changePassword(String username,String password){
        Optional<User> users = userRepository.findByUsername(username);
        User user = new User();
        if (!users.isEmpty()){
            user = users.get();
            user.setPassword(password);
            userRepository.save(user);
        }
        return user;
    }
}
