package com.example.back_end.Service;

import com.example.back_end.Model.Question;
import com.example.back_end.Model.Questionnaire;
import com.example.back_end.Model.QuestionnaireJunction;
import com.example.back_end.Repository.QuestionRepository;
import com.example.back_end.Repository.QuestionnaireJunctionRepository;
import com.example.back_end.Repository.QuestionnaireRepository;
import com.opencsv.CSVReader;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;

@Service
public class CsvLoaderService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionnaireJunctionRepository junctionRepository;

    @PostConstruct
    public void loadData() {
        loadQuestions();
        loadQuestionnaires();
        loadQuestionnaireJunctions();
    }

    public void loadQuestions() {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(this.getClass().getResourceAsStream("/csv/questionnaire_questions.csv")))) {
            String[] values;
            csvReader.readNext();

            while ((values = csvReader.readNext()) != null) {
                Long id = Long.parseLong(values[0]);
                String questionText = values[1];

                Question question = new Question(questionText);
                question.setId(id);
                questionRepository.save(question);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void loadQuestionnaires() {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(this.getClass().getResourceAsStream("/csv/questionnaire_questionnaires.csv")))) {
            String[] values;
            csvReader.readNext();

            while ((values = csvReader.readNext()) != null) {
                Long id = Long.parseLong(values[0]);
                String name = values[1];

                Questionnaire questionnaire = new Questionnaire(name);
                questionnaire.setId(id); // Set the ID manually if not auto-generated
                questionnaireRepository.save(questionnaire);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadQuestionnaireJunctions() {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(this.getClass().getResourceAsStream("/csv/questionnaire_junction.csv")))) {
            String[] values;
            csvReader.readNext();

            while ((values = csvReader.readNext()) != null) {
                Long id = Long.parseLong(values[0]);
                Long questionId = Long.parseLong(values[1]);
                Long questionnaireId = Long.parseLong(values[2]);
                int priority = Integer.parseInt(values[3]);

                Questionnaire questionnaire = questionnaireRepository.findById(questionnaireId).orElseThrow();
                Question question = questionRepository.findById(questionId).orElseThrow();

                if (questionnaire != null && question != null) {
                    QuestionnaireJunction junction = new QuestionnaireJunction(questionnaire, question, priority);
                    junction.setId(id); // Set the ID manually if not auto-generated
                    junctionRepository.save(junction);
                } else {
                    // Log or handle the case where either the questionnaire or question is missing
                    System.out.println("Missing questionnaire or question for junction ID: " + id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
