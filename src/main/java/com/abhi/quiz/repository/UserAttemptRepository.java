package com.abhi.quiz.repository;

import com.abhi.quiz.model.UserAttempt;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserAttemptRepository extends MongoRepository<UserAttempt, String> {
    List<UserAttempt> findByUserIdAndQuizId(String userId, String quizId);

    List<UserAttempt> findByUserId(String id);
}
