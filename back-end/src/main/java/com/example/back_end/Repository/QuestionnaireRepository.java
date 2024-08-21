package com.example.back_end.Repository;

import com.example.back_end.Model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {

}
