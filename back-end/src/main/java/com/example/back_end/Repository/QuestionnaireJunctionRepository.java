package com.example.back_end.Repository;

import com.example.back_end.Model.QuestionnaireJunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnaireJunctionRepository extends JpaRepository<QuestionnaireJunction, Long> {
    List<QuestionnaireJunction> findByQuestionnaireId(Long questionnaireId);
}
