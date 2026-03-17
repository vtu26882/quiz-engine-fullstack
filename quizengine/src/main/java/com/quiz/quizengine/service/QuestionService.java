package com.quiz.quizengine.service;

import com.quiz.quizengine.entity.Question;
import com.quiz.quizengine.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    // Get all questions
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    // Add new question
    public String addQuestion(Question question) {
        questionRepository.save(question);
        return "Question added successfully";
    }

    // Get all categories
    public List<String> getCategories() {
        return questionRepository.findDistinctCategories();
    }

    // Get random questions by category
    public List<Question> getQuestionsByCategory(String category, int numQ) {
        return questionRepository.findRandomQuestionsByCategory(category, numQ);
    }
}