package com.example.back_end.Controller;

import com.example.back_end.Model.Question;
import com.example.back_end.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = this.questionRepository.findAll();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Question>> getQuestionsByQuestionnaire(@PathVariable Long id) {
        List<Question> question = this.questionRepository.findQuestionsByQuestionnaireId(id);
        return ResponseEntity.ok(question);
    }




}
