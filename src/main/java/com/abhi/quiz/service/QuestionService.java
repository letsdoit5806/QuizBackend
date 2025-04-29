package com.abhi.quiz.service;

import com.abhi.quiz.model.Question;
import com.abhi.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Optional<Question> getQuestionById(String id) {
        return questionRepository.findById(id);
    }

    public List<Question> getQuestionsByIds(List<String> ids) {
        return questionRepository.findAllById(ids);
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

}
