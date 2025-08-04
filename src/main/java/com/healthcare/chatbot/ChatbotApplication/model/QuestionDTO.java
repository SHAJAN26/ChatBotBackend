package com.healthcare.chatbot.ChatbotApplication.model;

import lombok.Data;
import java.util.List;

@Data
public class QuestionDTO {
    private String id;
    private String text;
    private String parentId;
    private int level;
    private boolean isRoot;
    private List<QuestionDTO> children;
}