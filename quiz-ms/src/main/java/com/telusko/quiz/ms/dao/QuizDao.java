package com.telusko.quiz.ms.dao;

import com.telusko.quiz.ms.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository <Quiz, Integer> {

}
