package com.healthcare.chatbot.ChatbotApplication.repository;

import com.healthcare.chatbot.ChatbotApplication.model.Question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {
    List<Question> findByParentIdIsNull();
    List<Question> findByParentId(String parentId);
}