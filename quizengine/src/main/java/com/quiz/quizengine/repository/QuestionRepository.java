package com.quiz.quizengine.repository;

import com.quiz.quizengine.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    // Find questions by category
    List<Question> findByCategory(String category);

    // Get distinct categories
    @Query(value = "SELECT DISTINCT category FROM questions", nativeQuery = true)
    List<String> findDistinctCategories();

    // Get random questions by category
    @Query(value = "SELECT * FROM questions WHERE category = ?1 ORDER BY RAND() LIMIT ?2", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}