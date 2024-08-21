package com.example.back_end.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@Entity
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "questionnaire_id", nullable = false)
    private Integer questionnaireId;

    @Column(name = "question_id", nullable = false)
    private Integer questionId;

    @Column(name = "answer", nullable = false)
    private String answer;
}
