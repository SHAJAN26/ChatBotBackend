package com.healthcare.chatbot.ChatbotApplication.service;

import com.healthcare.chatbot.ChatbotApplication.model.Question;
import com.healthcare.chatbot.ChatbotApplication.model.QuestionDTO;
import com.healthcare.chatbot.ChatbotApplication.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    
    private final QuestionRepository questionRepository;
    
    @Transactional
    public void saveAllQuestions(List<QuestionDTO> questionDTOs) {
        questionRepository.deleteAll();
        saveQuestionsRecursive(questionDTOs, null);
    }
    
    private void saveQuestionsRecursive(List<QuestionDTO> questionDTOs, String parentId) {
        for (QuestionDTO dto : questionDTOs) {
            Question question = new Question();
            question.setId(dto.getId());
            question.setText(dto.getText());
            question.setParentId(parentId);
            question.setLevel(dto.getLevel());
            question.setRoot(dto.isRoot());
            
            questionRepository.save(question);
            
            if (dto.getChildren() != null && !dto.getChildren().isEmpty()) {
                saveQuestionsRecursive(dto.getChildren(), dto.getId());
            }
        }
    }
    
    public List<Question> getAllQuestions() {
        List<Question> rootQuestions = questionRepository.findByParentIdIsNull();
        return rootQuestions.stream()
                .map(this::buildQuestionTree)
                .collect(Collectors.toList());
    }
    
    private Question buildQuestionTree(Question question) {
        List<Question> children = questionRepository.findByParentId(question.getId());
        children = children.stream()
                .map(this::buildQuestionTree)
                .collect(Collectors.toList());
        question.setChildren(children);
        return question;
    }
}