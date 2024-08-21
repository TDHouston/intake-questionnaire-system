package com.example.back_end.Repository;

import com.example.back_end.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT q FROM Question q JOIN QuestionnaireJunction jq ON q.id = jq.question.id WHERE jq.questionnaire.id = :questionnaire_id")
    List<Question> findQuestionsByQuestionnaireId(@Param("questionnaire_id") Long questionnaireId);
}
