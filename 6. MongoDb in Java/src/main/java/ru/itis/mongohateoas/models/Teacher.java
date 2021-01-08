package ru.itis.mongohateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "teachers")
public class Teacher {
    @Id
    private String _id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer bonus;
    private String current;
    private String position;
    @DBRef
    private StudentGroup studentGroup;
    @DBRef
    private List<Course> courses;

}
