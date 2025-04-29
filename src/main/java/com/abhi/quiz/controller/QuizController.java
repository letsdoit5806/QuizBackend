package com.abhi.quiz.controller;

import com.abhi.quiz.dto.CreateQuizRequest;
import com.abhi.quiz.dto.FullQuizDto;
import com.abhi.quiz.dto.QuizSummary;
import com.abhi.quiz.model.Quiz;
import com.abhi.quiz.response.ApiResponse;
import com.abhi.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;


    // Endpoint to add a new quiz
    @PostMapping("/create")
    public Quiz createQuiz(@RequestBody CreateQuizRequest request) {
        return quizService.createQuiz(request);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllQuizzes() {
        return ResponseEntity.ok(new ApiResponse("success",quizService.getAllQuizzes()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getQuizWithQuestions(@PathVariable String id) {
        return ResponseEntity.ok(new ApiResponse("success",quizService.getQuizWithQuestions(id)));
    }


}
