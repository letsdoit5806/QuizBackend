package com.abhi.quiz.repository;

import com.abhi.quiz.model.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizRepository extends MongoRepository<Quiz, String> {
}



