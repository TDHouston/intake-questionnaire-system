package com.example.back_end.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class QuestionnaireJunction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "questionnaire_id", nullable = false)
    private Questionnaire questionnaire;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    private int priority;

    public QuestionnaireJunction() {
    }

    public QuestionnaireJunction(Questionnaire questionnaire, Question question, int priority) {
        this.questionnaire = questionnaire;
        this.question = question;
        this.priority = priority;
    }

}
