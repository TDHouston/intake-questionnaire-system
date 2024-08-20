package com.example.back_end.Service;

import com.example.back_end.Model.Question;
import com.example.back_end.Model.Questionnaire;
import com.example.back_end.Model.QuestionnaireJunction;
import com.example.back_end.Repository.QuestionRepository;
import com.example.back_end.Repository.QuestionnaireJunctionRepository;
import com.example.back_end.Repository.QuestionnaireRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.List;

@Service
public class CsvLoaderService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionnaireJunctionRepository junctionRepository;

    public void loadQuestions(MultipartFile file) {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                Long id = Long.parseLong(values[0]);
                String questionText = values[1];

                Question question = new Question(questionText);
                question.setId(id); // Set the ID manually if not auto-generated
                questionRepository.save(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadQuestionnaires(MultipartFile file) {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] values;
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

    public void loadQuestionnaireJunctions(MultipartFile file) {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                Long id = Long.parseLong(values[0]);
                Long questionId = Long.parseLong(values[1]);
                Long questionnaireId = Long.parseLong(values[2]);
                int priority = Integer.parseInt(values[3]);

                Questionnaire questionnaire = questionnaireRepository.findById(questionnaireId).orElseThrow();
                Question question = questionRepository.findById(questionId).orElseThrow();

                QuestionnaireJunction junction = new QuestionnaireJunction(questionnaire, question, priority);
                junction.setId(id); // Set the ID manually if not auto-generated
                junctionRepository.save(junction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
