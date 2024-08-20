package com.example.back_end;

import com.example.back_end.Service.CsvLoaderService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntakeQuestionnaireSystemApplication {

	@Autowired
	private CsvLoaderService csvLoaderService;

	public static void main(String[] args) {
		SpringApplication.run(IntakeQuestionnaireSystemApplication.class, args);
	}

	@PostConstruct
	public void init() {
		csvLoaderService.loadData();
	}
}
