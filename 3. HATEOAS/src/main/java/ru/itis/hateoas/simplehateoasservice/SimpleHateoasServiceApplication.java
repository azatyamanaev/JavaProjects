package ru.itis.hateoas.simplehateoasservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.hateoas.simplehateoasservice.models.*;
import ru.itis.hateoas.simplehateoasservice.repositories.*;

import java.util.Collections;

import static java.util.Arrays.asList;

@SpringBootApplication
public class SimpleHateoasServiceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SimpleHateoasServiceApplication.class, args);

        CoursesRepository coursesRepository = context.getBean(CoursesRepository.class);
        StudentsRepository studentsRepository = context.getBean(StudentsRepository.class);
        GroupsRepository groupsRepository = context.getBean(GroupsRepository.class);
        DocumentsRepository documentsRepository = context.getBean(DocumentsRepository.class);
        TeachersRepository teachersRepository = context.getBean(TeachersRepository.class);
        InstitutesRepository institutesRepository = context.getBean(InstitutesRepository.class);

        Course javaLab = Course.builder()
                .description("Курс по разработке на Java")
                .title("JavaLab")
                .state("Deleted")
                .count(2)
                .build();

        Course dataLab = Course.builder()
                .description("Курс по Базам данных")
                .title("DataLab")
                .state("Draft")
                .count(2)
                .build();

        Course testing = Course.builder()
                .description("Курс по тестированию")
                .title("УКРПО")
                .state("Published")
                .count(2)
                .build();

        Course info = Course.builder()
                .description("Курс по программированию на Java")
                .title("Информатика")
                .state("Deleted")
                .count(2)
                .build();

        Course dataAnalysis = Course.builder()
                .description("Курс о навыках аналитика")
                .title("Анализ данных(Мангушева)")
                .state("Draft")
                .count(2)
                .build();

        Course physics = Course.builder()
                .description("Физика")
                .title("Физика")
                .state("Draft")
                .count(3)
                .build();

        coursesRepository.saveAll(asList(
                javaLab, dataLab, testing, info, dataAnalysis, physics
        ));

        Institute itis = Institute.builder()
                .name("ИТИС КФУ")
                .city("Kazan")
                .address("Кремлевская, 35")
                .build();

        Institute physicsInstitute = Institute.builder()
                .name("Институт физики КФУ")
                .city("Kazan")
                .address("Кремлевская, 16А")
                .build();

        institutesRepository.saveAll(asList(itis, physicsInstitute));

        Teacher marsel = Teacher.builder()
                .firstName("Марсель")
                .lastName("Сидиков")
                .position("преподаватель, б.с.")
                .courses(asList(javaLab, info))
                .age(26)
                .bonus(0)
                .current("Suggested")
                .build();

        Teacher alina = Teacher.builder()
                .firstName("Алина")
                .lastName("Мангушева")
                .position("доцент, к.н.")
                .courses(asList(dataAnalysis))
                .age(25)
                .bonus(0)
                .current("Normal")
                .build();


        Teacher aigul = Teacher.builder()
                .firstName("Айгуль")
                .lastName("Мутыгуллина")
                .position("доцент, к.н.")
                .courses(asList(physics))
                .age(25)
                .bonus(0)
                .current("Normal")
                .build();

        teachersRepository.saveAll(asList(marsel, alina, aigul));

        StudentGroup group11802 = StudentGroup.builder()
                .name("11-802")
                .institute(itis)
                .curator(marsel)
                .build();

        StudentGroup group11806 = StudentGroup.builder()
                .name("11-806")
                .institute(itis)
                .curator(aigul)
                .build();

        groupsRepository.saveAll(asList(group11802, group11806));

        Student daria = Student.builder()
                .firstName("Дария")
                .lastName("Шагиева")
                .state("Undergraduate")
                .age(19)
                .year(3)
                .studentGroup(group11806)
                .courses(asList(javaLab, dataLab))
                .scholarship("Normal")
                .build();

        Student emil = Student.builder()
                .firstName("Эмиль")
                .lastName("Аминов")
                .state("Expelled")
                .age(19)
                .year(2)
                .studentGroup(group11806)
                .courses(Collections.singletonList(javaLab))
                .scholarship("Absent")
                .build();

        Student azat = Student.builder()
                .firstName("Азат")
                .lastName("Яманаев")
                .state("Undergraduate")
                .age(19)
                .year(3)
                .studentGroup(group11802)
                .courses(asList(javaLab, testing, dataAnalysis))
                .scholarship("Increased")
                .build();

        Student aidar = Student.builder()
                .firstName("Айдар")
                .lastName("Шайдуллин")
                .state("Enrollee")
                .age(20)
                .year(3)
                .studentGroup(group11802)
                .courses(asList(javaLab, testing))
                .scholarship("Normal")
                .build();

        Student airat = Student.builder()
                .firstName("Айрат")
                .lastName("Гатин")
                .state("Graduate")
                .age(21)
                .year(3)
                .studentGroup(group11802)
                .courses(asList(javaLab, testing))
                .scholarship("Absent")
                .build();

        Student kamilla = Student.builder()
                .firstName("Камилла")
                .lastName("Хайруллина")
                .state("Undergraduate")
                .age(20)
                .year(3)
                .studentGroup(group11802)
                .courses(asList(javaLab, testing, physics))
                .scholarship("Increased")
                .build();

        Student dmitrii = Student.builder()
                .firstName("Дмитрий")
                .lastName("Рябов")
                .state("Enrollee")
                .age(20)
                .year(3)
                .studentGroup(group11802)
                .courses(asList(javaLab, testing, physics))
                .scholarship("Normal")
                .build();

        studentsRepository.saveAll(asList(emil, daria, azat, aidar, airat, kamilla, dmitrii));
    }

}
