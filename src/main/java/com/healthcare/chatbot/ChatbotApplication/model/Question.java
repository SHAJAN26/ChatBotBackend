package com.healthcare.chatbot.ChatbotApplication.model;

import lombok.Data;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
@Data
public class Question {
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "text", length = 1000)
    private String text;
    
    @Column(name = "parent_id")
    private String parentId;
    
    @Column(name = "level")
    private int level;
    
    @Column(name = "is_root")
    private boolean isRoot;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private List<Question> children = new ArrayList<>();
}