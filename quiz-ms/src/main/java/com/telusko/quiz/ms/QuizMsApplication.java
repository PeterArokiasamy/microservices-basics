package com.telusko.quiz.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizMsApplication.class, args);
	}

}
