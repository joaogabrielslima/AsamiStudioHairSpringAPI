package com.br.asamistudiohair.service;

import com.br.asamistudiohair.model.User;
import com.br.asamistudiohair.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User findByPassword(String password){
        return userRepository.findUserByPassword(password);
    }

    public User findByPasswordAndEmail(String passord, String email) {
        return userRepository.findUserByPasswordAndEmail(passord, email);
    }

}
