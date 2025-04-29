package com.abhi.quiz.repository;

import com.abhi.quiz.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUid(String uid);
}
