package com.example.back_end.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "questionnaire_id", nullable = false)
    private Integer questionnaireId;

    @Column(name = "question_id", nullable = false)
    private Integer questionId;

    @ElementCollection
    @Column(name = "answer", nullable = false)
    private List<String> answer;
}
