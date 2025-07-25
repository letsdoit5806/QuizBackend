package com.abhi.quiz.controller;

import com.abhi.quiz.model.User;
import com.abhi.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{uid}")
    public User getUserByUid(@PathVariable String uid) {
        return userService.getUserByFirebaseUid(uid);
    }
}
