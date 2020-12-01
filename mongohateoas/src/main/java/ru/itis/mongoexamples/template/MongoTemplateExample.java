package ru.itis.mongoexamples.template;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class MongoTemplateExample {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RepositoriesConfig.class);
        CoursesRepository coursesRepository = context.getBean(CoursesRepository.class);
        StudentsRepository studentsRepository = context.getBean(StudentsRepository.class);

        Course course = Course.builder()
                .description("simple course")
                .state("Deleted")
                .title("course")
                .build();

        Course course1 = Course.builder()
                .description("difficult course")
                .state("Draft")
                .title("алгем")
                .build();

        coursesRepository.saveAll(Arrays.asList(course, course1));
        coursesRepository.findAll().forEach(System.out::println);
        coursesRepository.delete(course);
        coursesRepository.findAll().forEach(System.out::println);
        course1.setState("Published");
        coursesRepository.save(course1);
        coursesRepository.findAll().forEach(System.out::println);
        coursesRepository.delete(course1);


        Student student = Student.builder()
                .firstName("Dmitrii")
                .lastName("Ryabov")
                .courses(Arrays.asList(course, course1))
                .build();

        studentsRepository.save(student);
        studentsRepository.findAll().forEach(System.out::println);
        studentsRepository.delete(student);


    }
}
