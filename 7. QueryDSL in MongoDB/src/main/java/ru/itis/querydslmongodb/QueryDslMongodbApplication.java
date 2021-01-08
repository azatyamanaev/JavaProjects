package ru.itis.querydslmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.querydslmongodb.models.Course;
import ru.itis.querydslmongodb.repositories.CoursesRepository;
import ru.itis.querydslmongodb.repositories.StudentsRepository;

@SpringBootApplication
public class QueryDslMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryDslMongodbApplication.class, args);
    }

}
