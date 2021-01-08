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
        ApplicationContext context = new AnnotationConfigApplicationContext(RepositoriesConfig.class);
        StudentsRepository studentsRepository = context.getBean(StudentsRepository.class);
        CoursesRepository coursesRepository = context.getBean(CoursesRepository.class);
        //SpringApplication.run(QueryDslMongodbApplication.class, args);

        studentsRepository.findAll().forEach(System.out::println);
        coursesRepository.findAll().forEach(System.out::println);
    }

}
