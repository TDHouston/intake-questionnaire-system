package com.example.back_end.Controller;

import com.example.back_end.Model.Answer;
import com.example.back_end.Repository.AnswersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AnswerController {

    @Autowired
    AnswersRepository answersRepository;

    @PostMapping("/answers")
    public ResponseEntity<String> submitAnswers(@RequestBody AnswerRequest request) {
        try {
            request.getAnswers().forEach(answerDTO -> {
                Answer a = new Answer();
                a.setUserId(request.getUserId());
                a.setQuestionnaireId(request.getQuestionnaireId());
                a.setQuestionId(answerDTO.getQuestionId());
                a.setAnswer(answerDTO.getAnswer());
                answersRepository.save(a);
            });
            return ResponseEntity.ok("Answers submitted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting answers.");
        }
    }
}
