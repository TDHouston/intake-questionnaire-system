package com.example.back_end.Repository;

import com.example.back_end.Model.QuestionnaireJunction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionnaireJunctionRepository extends JpaRepository<QuestionnaireJunction, Long> {
    List<QuestionnaireJunction> findByQuestionnaireId(Long questionnaireId);
}
