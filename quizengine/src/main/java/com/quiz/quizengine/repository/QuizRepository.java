package com.quiz.quizengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quiz.quizengine.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}