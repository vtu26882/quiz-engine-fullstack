package com.quiz.quizengine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.quiz.quizengine.entity.User;
import com.quiz.quizengine.repository.UserRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public String register(@RequestBody User user){

        User existingUser = userRepository.findByUsername(user.getUsername());

        if(existingUser != null){
            return "USERNAME ALREADY EXISTS";
        }

        userRepository.save(user);

        return "USER REGISTERED SUCCESSFULLY";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){

        User existingUser = userRepository.findByUsername(user.getUsername());

        if(existingUser != null && existingUser.getPassword().equals(user.getPassword())){
            return "LOGIN SUCCESS";
        }

        return "INVALID USER";
    }
}