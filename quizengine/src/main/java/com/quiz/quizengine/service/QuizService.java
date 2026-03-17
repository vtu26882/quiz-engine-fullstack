package com.quiz.quizengine.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.quizengine.entity.Leaderboard;
import com.quiz.quizengine.entity.Question;
import com.quiz.quizengine.entity.Quiz;
import com.quiz.quizengine.entity.QuestionWrapper;
import com.quiz.quizengine.repository.LeaderboardRepository;
import com.quiz.quizengine.repository.QuestionRepository;
import com.quiz.quizengine.repository.QuizRepository;
import com.quiz.quizengine.entity.Response;

@Service
public class QuizService {
    @Autowired
CertificateService certificateService;

    @Autowired
    QuizRepository quizRepo;

    @Autowired
    QuestionRepository questionRepo;

    @Autowired
LeaderboardRepository leaderboardRepo;

    

    // METHOD 1 → CREATE QUIZ
    public String createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionRepo.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();

        quiz.setTitle(title);

       quiz.setStartTime(System.currentTimeMillis());


       Collections.shuffle(questions);
String ids = questions.stream()
        .limit(numQ)
        .map(q -> String.valueOf(q.getId()))
        .reduce((a,b) -> a + "," + b)
        .orElse("");

        quiz.setQuestionIds(ids);

        quizRepo.save(quiz);

        return "Quiz created successfully";
    }

    // METHOD 2 → GET QUIZ QUESTIONS
    public List<QuestionWrapper> getQuizQuestions(Integer id) {

        Quiz quiz = quizRepo.findById(id).get();

        List<Integer> questionIds = Arrays.stream(quiz.getQuestionIds().split(","))
                .map(Integer::parseInt)
                .toList();

        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for (Integer qId : questionIds) {

            Question q = questionRepo.findById(qId).get();

            List<String> options = new ArrayList<>();

options.add(q.getOption1());
options.add(q.getOption2());
options.add(q.getOption3());
options.add(q.getOption4());

Collections.shuffle(options);

            QuestionWrapper wrapper = new QuestionWrapper(
                    q.getId(),
                    q.getQuestionTitle(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4()
            );

            questionsForUser.add(wrapper);
        }
        

        return questionsForUser;
    }
    public Map<String,Integer> submitQuiz(Integer id, String username, List<Response> responses) {

    Quiz quiz = quizRepo.findById(id).get();

    List<Integer> questionIds = Arrays.stream(quiz.getQuestionIds().split(","))
            .map(Integer::parseInt)
            .toList();

    int score = 0;

    for(int i=0;i<questionIds.size();i++){

        Question question = questionRepo.findById(questionIds.get(i)).get();

        if(responses.get(i).getResponse().equals(question.getCorrectAnswer())){
            score++;
        }
    }

   Map<String,Integer> result = new HashMap<>();

result.put("score", score);
result.put("total", questionIds.size());

Leaderboard leaderboard = new Leaderboard();
leaderboard.setQuizId(id);
leaderboard.setUsername(username); // later this can come from login
leaderboard.setScore(score);

leaderboardRepo.save(leaderboard);

if(score >= questionIds.size() / 2) {
    certificateService.generateCertificate(username, quiz.getTitle(), score, questionIds.size());
}

return result;
}
    

}