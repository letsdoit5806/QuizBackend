package com.abhi.quiz.controller;

import com.abhi.quiz.model.UserAttempt;
import com.abhi.quiz.service.UserAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/attempts")
public class UserAttemptController {

    private final UserAttemptService userAttemptService;

    @Autowired
    public UserAttemptController(UserAttemptService userAttemptService) {
        this.userAttemptService = userAttemptService;
    }

    @PostMapping("/submit/{uid}")
    public UserAttempt submitQuizAttempt(@PathVariable String uid ,@RequestBody UserAttempt userAttempt) {
        return userAttemptService.processAndSaveAttempt(userAttempt ,uid);
    }
}
