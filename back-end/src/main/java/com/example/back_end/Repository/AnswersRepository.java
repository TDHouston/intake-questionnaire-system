package com.example.back_end.Repository;

import com.example.back_end.Model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswersRepository extends JpaRepository<Answer, Long> {
}
