package com.quiz.quizengine.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.quiz.quizengine.entity.Leaderboard;
import com.quiz.quizengine.entity.QuestionWrapper;
import com.quiz.quizengine.entity.QuizRequest;
import com.quiz.quizengine.service.QuizService;
import com.quiz.quizengine.entity.Response;
import com.quiz.quizengine.repository.LeaderboardRepository;
@RestController
@RequestMapping("/quiz")
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {

    @Autowired
    QuizService quizService;
    @Autowired
LeaderboardRepository leaderboardRepo;
  
@PostMapping("/submit/{id}")
public Map<String,Integer> submitQuiz(
        @PathVariable Integer id,
        @RequestParam String username,
        @RequestBody List<Response> responses){

    return quizService.submitQuiz(id, username, responses);
}

    // API 1 → CREATE QUIZ
    @PostMapping("/create")
public String createQuiz(@RequestBody QuizRequest request) {

    return quizService.createQuiz(
            request.getCategory(),
            request.getNumQ(),
            request.getTitle()
    );
}

    // API 2 → GET QUIZ QUESTIONS
    @GetMapping("/get/{id}")
    public List<QuestionWrapper> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @GetMapping("/leaderboard/{quizId}")
public List<Leaderboard> getLeaderboard(@PathVariable Integer quizId){
    return leaderboardRepo.findByQuizIdOrderByScoreDesc(quizId);
}

}