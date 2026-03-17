package com.quiz.quizengine.controller;

import com.quiz.quizengine.entity.Question;
import com.quiz.quizengine.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    // Get all questions
    @GetMapping("/allQuestions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // Add a question
    @PostMapping("/add")
    public String addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }
    @GetMapping("/categories")
public List<String> getCategories() {
    return questionService.getCategories();
}

     @GetMapping("/category/{category}/{numQ}")
public List<Question> getQuestionsByCategory(
        @PathVariable String category,
        @PathVariable int numQ) {

    return questionService.getQuestionsByCategory(category, numQ);
}
}