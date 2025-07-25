package com.abhi.quiz.controller;

import com.abhi.quiz.dto.AttemptId;
import com.abhi.quiz.dto.AttemptedQuizSummary;
import com.abhi.quiz.dto.CombineResponse;
import com.abhi.quiz.model.UserAttempt;
import com.abhi.quiz.response.ApiResponse;
import com.abhi.quiz.service.UserAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public AttemptId submitQuizAttempt(@PathVariable String uid , @RequestBody UserAttempt userAttempt) {
        return userAttemptService.processAndSaveAttempt(userAttempt ,uid);
    }

    @GetMapping("/attempted/{uid}")
    public ResponseEntity<ApiResponse>getAllAttemptedQuiz(@PathVariable String uid){
        List<AttemptedQuizSummary> attemptedQuizSummaries =userAttemptService.getAllAttemptedQuiz(uid);
        return ResponseEntity.ok(new ApiResponse("success",attemptedQuizSummaries));
    }

    @GetMapping("/attempted/results/{id}")
    public ResponseEntity<CombineResponse>getAllAttemptedQuizResult(@PathVariable String id){

        CombineResponse response=userAttemptService.getAllAttemptedQuizResult(id);
        return ResponseEntity.ok(response);
    }


}
