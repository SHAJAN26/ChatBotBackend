package com.healthcare.chatbot.ChatbotApplication.controller;

import com.healthcare.chatbot.ChatbotApplication.model.Question;
import com.healthcare.chatbot.ChatbotApplication.model.QuestionDTO;
import com.healthcare.chatbot.ChatbotApplication.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
    
    private final QuestionService questionService;
    
    @PostMapping("/save")
    public void saveQuestions(@RequestBody List<QuestionDTO> questions) {
        questionService.saveAllQuestions(questions);
    }
    
    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }
}