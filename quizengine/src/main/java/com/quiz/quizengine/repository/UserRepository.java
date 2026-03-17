package com.quiz.quizengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quiz.quizengine.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

    User findByUsername(String username);

}