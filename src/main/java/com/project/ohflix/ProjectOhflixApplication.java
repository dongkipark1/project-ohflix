package com.project.ohflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectOhflixApplication {

    public static void main(String[] args) {
        // .env 파일에서 환경 변수 설정
//        Dotenv dotenv = Dotenv.load();
//
//        // .env 파일에서 환경 변수 설정
//        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
        SpringApplication.run(ProjectOhflixApplication.class, args);
    }

}
