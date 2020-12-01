package ru.itis.mongoexamples.spring;

import com.mongodb.BasicDBObjectBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import java.util.List;

public class MongoRepositoryExample {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MongoConfig.class);
        MongoTemplate template = context.getBean(MongoTemplate.class);

        Student student = Student.builder()
                .averageMark(90)
                .firstName("Azat")
                .patronymic("Reshadovich")
                .build();

        template.save(student, "students");
        Student s = template.findOne(new Query(where("firstName").is("Azat")), Student.class, "students");
        System.out.println(s.toString());
        s.setAverageMark(85);
        UpdateDefinition update = new Update().set("patronymic", "Almazovich");
        template.updateFirst(new Query(where("firstName").is("Azat")), update, Student.class, "students");
        s = template.findOne(new Query(where("firstName").is("Azat")), Student.class, "students");
        System.out.println(s.toString());
        template.remove(s);
        List<Student> students = template.findAll(Student.class);
        for (Student student1 : students) {
            System.out.println(student1.toString());
        }
    }
}
