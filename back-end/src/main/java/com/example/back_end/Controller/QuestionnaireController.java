package com.example.back_end.Controller;

import com.example.back_end.Model.Questionnaire;
import com.example.back_end.Repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questionnaires")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @GetMapping
    public ResponseEntity<List<Questionnaire>> getAllQuestionnaires() {
        List<Questionnaire> questionnaires = this.questionnaireRepository.findAll();
        return ResponseEntity.ok(questionnaires);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Questionnaire>> getQuestionnaireById(@PathVariable Long id) {
        Optional<Questionnaire> questionnaire = this.questionnaireRepository.findById(id);
        return ResponseEntity.ok(questionnaire);
    }
}
