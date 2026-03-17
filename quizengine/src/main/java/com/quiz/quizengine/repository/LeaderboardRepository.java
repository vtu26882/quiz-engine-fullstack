package com.quiz.quizengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.quizengine.entity.Leaderboard;

public interface LeaderboardRepository extends JpaRepository<Leaderboard, Integer> {

    List<Leaderboard> findByQuizIdOrderByScoreDesc(Integer quizId);

}